package com.example.poketeambuilder.data.datasources

import android.content.Context
import androidx.sqlite.db.SimpleSQLiteQuery
import com.example.poketeambuilder.R
import com.example.poketeambuilder.data.dao.PokeDao
import com.example.poketeambuilder.data.toPokemon
import com.example.poketeambuilder.data.toPokeEntity
import com.example.poketeambuilder.data.toPokemonList
import com.example.poketeambuilder.domain.Pokemon
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LocalPokeDataSourceImpl @Inject constructor(
    private val pokeDao: PokeDao,
    @ApplicationContext private val context: Context
) : LocalPokeDataSource {


    override suspend fun getAllPokemon(): List<Pokemon> {
        val pokeList = mutableListOf<Pokemon>()

        for (i in 1..context.resources.getInteger(R.integer.highest_id)) {
            pokeList.add(pokeDao.getPokeById(i).toPokemon())
        }

        return pokeList
    }

    override suspend fun addPokes(pokes: List<Pokemon>) {
        for (poke in pokes) {
            pokeDao.insert(poke.toPokeEntity())
        }
    }

    override suspend fun containsAllPokemon(): Boolean {
        return pokeDao.getDatabaseSize() == context.resources.getInteger(R.integer.highest_id)
    }

    override suspend fun getFilteredPokemon(
        nameOrId: String?,
        mainType: String?,
        secondaryType: String?
    ): List<Pokemon> {
        var query = ""
        val args: MutableList<Any> = mutableListOf()
        var containsConditions = false

        query += "SELECT * FROM pokeentity"

        nameOrId?.let { value ->
            when (nameOrId.toIntOrNull()) {
                null -> {
                    query += " WHERE poke_name LIKE ?"
                    args.add("%$value%")
                }

                else -> {
                    query += " WHERE id = ?"
                    args.add(value)
                }
            }

            containsConditions = true
        }

        mainType?.let { value ->
            if (value != "All") {
                if (containsConditions) {
                    query += " AND"
                } else {
                    query += " WHERE"
                    containsConditions = true
                }
                query += " poke_main_type = ?"
                args.add(value)
            }
        }

        secondaryType?.let { value ->
            if (value != "All") {
                if (containsConditions) {
                    query += " AND"
                } else {
                    query += " WHERE"
                    containsConditions = true
                }
                query += " poke_secondary_type = ?"
                args.add(value)
            }
        }

        query += ";"

        val finishedQuery = SimpleSQLiteQuery(query, args.toList().toTypedArray())

        return pokeDao.getFilteredPokemons(finishedQuery).toPokemonList()
    }

}