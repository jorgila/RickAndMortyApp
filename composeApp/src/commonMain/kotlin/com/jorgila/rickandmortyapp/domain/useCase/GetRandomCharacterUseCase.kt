package com.jorgila.rickandmortyapp.domain.useCase

import com.jorgila.rickandmortyapp.domain.model.CharacterModel
import com.jorgila.rickandmortyapp.domain.repository.Repository

class GetRandomCharacterUseCase(private val repository: Repository) {
    suspend operator fun invoke() : CharacterModel {
        val random : Int = (1 .. 826).random()
        return repository.getSingleCharacter(random.toString())
    }
}