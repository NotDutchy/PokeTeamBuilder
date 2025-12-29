package com.example.poketeambuilder.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Move(
    val id: Int,
    val name: String,
    val url: String,
): Parcelable

@Parcelize
data class MoveStats(
    val ailment: String,
    val chance: Int,
    val critRate: Int,
) : Parcelable
