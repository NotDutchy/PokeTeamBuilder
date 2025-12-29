package com.example.poketeambuilder.data.datasources

import com.example.poketeambuilder.domain.Pokemon

interface RemotePokeDataSource {
    suspend fun getAllPokemon(): List<Pokemon>
}