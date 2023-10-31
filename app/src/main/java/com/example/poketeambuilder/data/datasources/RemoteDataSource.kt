package com.example.poketeambuilder.data.datasources

import com.example.poketeambuilder.domain.Pokemon

interface RemoteDataSource {
    suspend fun getAllPokemon(): List<Pokemon>
}