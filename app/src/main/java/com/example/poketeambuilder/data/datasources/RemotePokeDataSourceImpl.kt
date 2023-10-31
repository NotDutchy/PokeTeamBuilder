package com.example.poketeambuilder.data.datasources

import android.content.Context
import com.example.poketeambuilder.R
import com.example.poketeambuilder.data.network.PokeApiService
import com.example.poketeambuilder.data.toPokemon
import com.example.poketeambuilder.domain.Pokemon
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class RemotePokeDataSourceImpl @Inject constructor(
    private val pokeApiService: PokeApiService,
    @ApplicationContext private val context: Context
) : RemotePokeDataSource {

    private suspend fun getPokeWithIdFromApi(pokeId: Int): Pokemon {
        val response = pokeApiService.GetPokemonFromApi(id = pokeId)
        return response.body()?.toPokemon() ?: throw Exception()
    }

    override suspend fun getAllPokemon(): List<Pokemon> {
        val pokeList = mutableListOf<Pokemon>()

        for (i in 1..context.resources.getInteger(R.integer.highest_id)) {
            pokeList.add(getPokeWithIdFromApi(i))
        }

        return pokeList.toList()
    }

}