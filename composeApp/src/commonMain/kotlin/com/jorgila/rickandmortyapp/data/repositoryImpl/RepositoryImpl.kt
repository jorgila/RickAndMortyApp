package com.jorgila.rickandmortyapp.data.repositoryImpl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import app.cash.paging.PagingData
import com.jorgila.rickandmortyapp.data.database.RickAndMortyDatabase
import com.jorgila.rickandmortyapp.data.database.entity.CharacterOfTheDayEntity
import com.jorgila.rickandmortyapp.data.remote.ApiService
import com.jorgila.rickandmortyapp.data.remote.paging.CharactersPagingSource
import com.jorgila.rickandmortyapp.data.remote.paging.EpisodesPagingSource
import com.jorgila.rickandmortyapp.domain.model.CharacterModel
import com.jorgila.rickandmortyapp.domain.model.CharacterOfTheDayModel
import com.jorgila.rickandmortyapp.domain.model.EpisodeModel
import com.jorgila.rickandmortyapp.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

class RepositoryImpl(
    private val api: ApiService,
    private val charactersPagingSource: CharactersPagingSource,
    private val episodesPagingSource: EpisodesPagingSource,
    private val rickAndMortyDatabase: RickAndMortyDatabase
) : Repository {

    companion object {
        const val MAX_ITEMS = 20
        const val PREFETCH_ITEMS = 5
    }

    override suspend fun getSingleCharacter(id: String): CharacterModel {
        return api.getSingleCharacter(id).toDomain()
    }

    override fun getAllCharacters(): Flow<PagingData<CharacterModel>> {
        return Pager(
            config = PagingConfig(pageSize = MAX_ITEMS, prefetchDistance = PREFETCH_ITEMS),
            pagingSourceFactory = { charactersPagingSource }
        ).flow
    }

    override fun getAllEpisodes(): Flow<PagingData<EpisodeModel>> {
        return Pager(
            config = PagingConfig(pageSize = MAX_ITEMS, prefetchDistance = PREFETCH_ITEMS),
            pagingSourceFactory = { episodesPagingSource }
        ).flow
    }

    override suspend fun getCharacterDB() : CharacterOfTheDayModel? {
        return rickAndMortyDatabase.getPreferencesDao().getCharacterOfTheDayDB()?.toDomain()
    }

    override suspend fun saveCharacterDB(characterOfTheDayModel: CharacterOfTheDayModel) {
        rickAndMortyDatabase.getPreferencesDao().saveCharacterDB(characterOfTheDayModel.toEntity())
    }

    override suspend fun getEpisodesForCharacter(episodes: List<String>) : List<EpisodeModel> {

        if(episodes.isEmpty()) return emptyList()

        return if(episodes.size > 1){
            api.getEpisodes(episodes.joinToString(",")).map { episodeResponse -> episodeResponse.toDomain() }
        } else {
            listOf(api.getSingleEpisode(episodes.first()).toDomain())
        }

    }
}
