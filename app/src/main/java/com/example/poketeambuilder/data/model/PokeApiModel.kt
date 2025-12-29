package com.example.poketeambuilder.data.model

import com.google.gson.annotations.SerializedName

data class PokeApiModel(
    val name: String,
    val id: Int,
    val moves: List<PokeMoveEntryApiModel>,
    val types: List<PokeTypeEntryApiModel>,
    val sprites: PokeSpriteApiModel,
    val height: Double,
    val weight: Double,
    val stats: List<PokeStatsEntryApiModel>
)

data class  PokeSpriteApiModel(
    val other: PokeSpriteOtherApiModel
)

data class PokeSpriteOtherApiModel(
    @SerializedName("official-artwork")
    val artwork: PokeSpriteOfficialApiModel
)

data class PokeSpriteOfficialApiModel(
    @SerializedName("front_default")
    val artworkUrl: String?
)

data class PokeStatsEntryApiModel(
    @SerializedName("base_stat")
    val baseStat: Int
)

data class PokeTypeEntryApiModel(
    val slot: Int,
    val type: PokeTypeApiModel
)

data class PokeTypeApiModel(
    val name: String
)
