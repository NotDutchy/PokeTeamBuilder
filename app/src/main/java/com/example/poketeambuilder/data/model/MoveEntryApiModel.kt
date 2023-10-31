package com.example.poketeambuilder.data.model

import com.google.gson.annotations.SerializedName

data class MoveEntryApiModel(
    val id: Int,
    val name: String,
    val type: PokeTypeApiModel,
    val damageClass: String,
    val accuracy: Int,
    val power: Int,
    val pp: Int,
    val meta: MetaEntryApiModel,
)

data class MetaEntryApiModel(
    val ailment: String,
    @SerializedName("ailment_chance")
    val chance: Int,
    @SerializedName("crit_rate")
    val critRate: Int,
)
