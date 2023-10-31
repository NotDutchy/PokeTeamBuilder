package com.example.poketeambuilder.data.repos

import com.example.poketeambuilder.data.datasources.LocalPokeDataSource
import com.example.poketeambuilder.data.datasources.RemotePokeDataSource
import com.example.poketeambuilder.domain.Pokemon

class PokeRepositoryImpl(
    private val remotePokeDataSource: RemotePokeDataSource,
    private val localPokeDataSource: LocalPokeDataSource
) : PokeRepository {

    override suspend fun getAllPokemon(): Result<List<Pokemon>> {
        return runCatching {
            if (!localPokeDataSource.containsAllPokemon()) {
                localPokeDataSource.addPokes(pokes = remotePokeDataSource.getAllPokemon())
            }

            localPokeDataSource.getAllPokemon()
        }
    }

    override suspend fun getFilteredPokemon(
        nameOrId: String?,
        mainType: String?,
        secondaryType: String?
    ): Result<List<Pokemon>> {
        return runCatching {
            localPokeDataSource.getFilteredPokemon(
                nameOrId = nameOrId,
                mainType = mainType,
                secondaryType = secondaryType
            )
        }
    }
}