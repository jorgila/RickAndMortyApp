package com.jorgila.rickandmortyapp.domain.repository

import app.cash.paging.PagingData
import com.jorgila.rickandmortyapp.domain.model.CharacterModel
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getSingleCharacter(id: String) : CharacterModel
    fun getAllCharacters() : Flow<PagingData<CharacterModel>>
}