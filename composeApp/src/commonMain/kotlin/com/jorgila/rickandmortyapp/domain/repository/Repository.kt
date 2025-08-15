package com.jorgila.rickandmortyapp.domain.repository

import app.cash.paging.PagingData
import com.jorgila.rickandmortyapp.data.database.entity.CharacterOfTheDayEntity
import com.jorgila.rickandmortyapp.domain.model.CharacterModel
import com.jorgila.rickandmortyapp.domain.model.CharacterOfTheDayModel
import com.jorgila.rickandmortyapp.domain.model.EpisodeModel
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getSingleCharacter(id: String) : CharacterModel
    fun getAllCharacters() : Flow<PagingData<CharacterModel>>
    fun getAllEpisodes() : Flow<PagingData<EpisodeModel>>
    suspend fun getCharacterDB(): CharacterOfTheDayModel?
    suspend fun saveCharacterDB(characterOfTheDayModel: CharacterOfTheDayModel)
    suspend fun getEpisodesForCharacter(episodes: List<String>): List<EpisodeModel>
}