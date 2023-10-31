package com.example.poketeambuilder.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokeEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "poke_name") val pokeName: String,
    @ColumnInfo(name = "poke_image_url") val pokeImageUrl: String,
    @ColumnInfo(name = "poke_main_type") val pokeMainType: String,
    @ColumnInfo(name = "poke_secondary_type") val pokeSecondaryType: String,
    @ColumnInfo(name = "poke_height") val pokeHeight: Double,
    @ColumnInfo(name = "poke_weight") val pokeWeight: Double,
    @ColumnInfo(name = "poke_stat_hp") val pokeStatHp: Int,
    @ColumnInfo(name = "poke_stat_att") val pokeStatAtt: Int,
    @ColumnInfo(name = "poke_stat_def") val pokeStatDef: Int,
    @ColumnInfo(name = "poke_stat_sp_att") val pokeStatSpAtt: Int,
    @ColumnInfo(name = "poke_stat_sp_def") val pokeStatSpDef: Int,
    @ColumnInfo(name = "poke_stat_spd") val pokeStatSpd: Int,
    val moves: List<MoveEntryApiModel>
)