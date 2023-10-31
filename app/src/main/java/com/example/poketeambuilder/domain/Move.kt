package com.example.poketeambuilder.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Move(
    val id: Int,
    val name: String,
    val type: String,
    val damageClass: String,
    val accuracy: Int,
    val power: Int,
    val pp: Int,
    val metaData: MetaData
): Parcelable

@Parcelize
data class MetaData(
    val ailment: String,
    val chance: Int,
    val critRate: Int,
) : Parcelable
