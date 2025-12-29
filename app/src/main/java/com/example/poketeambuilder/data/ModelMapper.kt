package com.example.poketeambuilder.data

import android.content.res.Resources
import com.example.poketeambuilder.R
import com.example.poketeambuilder.data.model.PokeApiModel
import com.example.poketeambuilder.data.model.PokeEntity
import com.example.poketeambuilder.domain.Move
import com.example.poketeambuilder.domain.Pokemon

fun List<PokeEntity>.toPokemonList(): List<Pokemon> {
    val pokeList = mutableListOf<Pokemon>()

    this.forEach { pokeEntity ->
        pokeList.add(pokeEntity.toPokemon())
    }

    return pokeList.toList()
}

fun PokeApiModel.toPokemon(): Pokemon {
    val typeList = mutableListOf<String>()
    this.types.forEach {pokeTypeEntry ->
        val type = pokeTypeEntry.type.name.replaceFirstChar { it.uppercase() }
        typeList.add(type)
    }
    val moveList = mutableListOf<Move>()

    this.moves.forEach { pokeMoveEntry ->
        val move = Move(
            pokeMoveEntry.id,
            pokeMoveEntry.move.name,
            pokeMoveEntry.move.url
        )
        moveList.add(move)
    }

    return Pokemon(
        name = this.name.replaceFirstChar { it.uppercase() },
        id = this.id,
        imageUrl = this.sprites.other.artwork.artworkUrl ?: Resources.getSystem().getString(R.string.default_egg_sprite),
        types = typeList.toList(),
        weight = this.weight,
        height = this.height,
        this.stats[0].baseStat,
        this.stats[1].baseStat,
        this.stats[2].baseStat,
        this.stats[3].baseStat,
        this.stats[4].baseStat,
        this.stats[5].baseStat,
        moves = moveList.toList()
    )
}

fun Pokemon.toPokeEntity(): PokeEntity = PokeEntity(
    id = this.id,
    pokeName = this.name,
    pokeImageUrl = this.imageUrl,
    pokeMainType = this.types[0],
    pokeSecondaryType = if (this.types.size > 1) this.types[1] else null,
    pokeHeight = this.height,
    pokeWeight = this.weight,
    pokeStatHp = this.hpStat,
    pokeStatAtt = this.attStat,
    pokeStatDef = this.defStat,
    pokeStatSpAtt = this.spAttStat,
    pokeStatSpDef = this.spDefStat,
    pokeStatSpd = this.spdStat,
    moves = this.moves
)

fun PokeEntity.toPokemon() : Pokemon {
    val types = mutableListOf<String>()
    types.add(this.pokeMainType)
    this.pokeSecondaryType?.let { type -> types.add(type) }

    return Pokemon(
        name = this.pokeName,
        id = this.id,
        imageUrl = this.pokeImageUrl,
        types = types.toList(),
        weight = this.pokeWeight,
        height = this.pokeHeight,
        hpStat = this.pokeStatHp,
        attStat = this.pokeStatAtt,
        defStat = this.pokeStatDef,
        spAttStat = this.pokeStatSpAtt,
        spDefStat = this.pokeStatSpDef,
        spdStat = this.pokeStatSpd,
        moves = this.moves,
    )
}
