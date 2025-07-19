package com.jorgila.rickandmortyapp.data.repositoryImpl

import com.jorgila.rickandmortyapp.data.remote.ApiService
import com.jorgila.rickandmortyapp.domain.model.CharacterModel
import com.jorgila.rickandmortyapp.domain.repository.Repository

class RepositoryImpl(private val api: ApiService) : Repository {
    override suspend fun getSingleCharacter(id: String): CharacterModel {
        return api.getSingleCharacter(id).toDomain()
    }
}