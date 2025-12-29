package com.example.poketeambuilder.domain.usecases

import com.example.poketeambuilder.data.repos.PokeRepository
import com.example.poketeambuilder.domain.Pokemon

class GetFilteredPokeUseCase(private val repo: PokeRepository) {
    suspend operator fun invoke(
        nameOrId: String? = null ,
        mainType: String? = null,
        secondaryType: String? = null,
    ): Result<List<Pokemon>> = repo.getFilteredPokemon(
        nameOrId = nameOrId,
        mainType = mainType,
        secondaryType = secondaryType
    )
}