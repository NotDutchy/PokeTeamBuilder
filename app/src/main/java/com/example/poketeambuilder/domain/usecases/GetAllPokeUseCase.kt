package com.example.poketeambuilder.domain.usecases

import com.example.poketeambuilder.data.repos.PokeRepository
import com.example.poketeambuilder.domain.Pokemon

class GetAllPokeUseCase(
    private val repo: PokeRepository
) {
    suspend operator fun invoke(): Result<List<Pokemon>> = repo.getAllPokemon();
}