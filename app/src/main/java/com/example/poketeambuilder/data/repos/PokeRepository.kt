package com.example.poketeambuilder.data.repos

import com.example.poketeambuilder.domain.Pokemon

interface PokeRepository {
    suspend fun getAllPokemon(): Result<List<Pokemon>>
    suspend fun getFilteredPokemon(
        nameOrId: String?,
        mainType: String?,
        secondaryType: String?
    ) : Result <List<Pokemon>>
}