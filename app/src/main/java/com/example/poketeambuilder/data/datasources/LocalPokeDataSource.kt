package com.example.poketeambuilder.data.datasources

import com.example.poketeambuilder.domain.Pokemon

interface LocalPokeDataSource {
    suspend fun getAllPokemon(): List<Pokemon>
    suspend fun addPokes(pokes: List<Pokemon>)
    suspend fun containsAllPokemon(): Boolean
    suspend fun getFilteredPokemon(
        nameOrId: String?,
        mainType: String?,
        secondaryType: String?
    ): List<Pokemon>
}