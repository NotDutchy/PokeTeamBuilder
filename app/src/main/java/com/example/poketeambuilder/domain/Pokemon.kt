package com.example.poketeambuilder.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pokemon(
    val name: String,
    val id: Int,
    val imageUrl: String,
    val types: List<String>,
    val weight: Double,
    val height: Double,
    val hpStat: Int,
    val attStat: Int,
    val defStat: Int,
    val spAttStat: Int,
    val spDefStat: Int,
    val spdStat: Int,
    val moves: List<Move>,
) : Parcelable