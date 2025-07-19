package com.jorgila.rickandmortyapp.domain.repository

import com.jorgila.rickandmortyapp.domain.model.CharacterModel

interface Repository {
    suspend fun getSingleCharacter(id: String) : CharacterModel
}