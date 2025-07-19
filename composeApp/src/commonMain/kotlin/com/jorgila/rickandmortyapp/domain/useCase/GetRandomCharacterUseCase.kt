package com.jorgila.rickandmortyapp.domain.useCase

import com.jorgila.rickandmortyapp.domain.repository.Repository

class GetRandomCharacterUseCase(private val repository: Repository) {
    suspend operator fun invoke(){
        val random : Int = (1 .. 826).random()
        repository.getSingleCharacter(random.toString())
    }
}