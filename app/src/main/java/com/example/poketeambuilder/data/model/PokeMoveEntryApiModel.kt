package com.example.poketeambuilder.data.model

data class PokeMoveEntryApiModel(
    val id: Int,
    val move: PokeMoveNameUrlApiModel
)

data class PokeMoveNameUrlApiModel(
    val name: String,
    val url: String
)
