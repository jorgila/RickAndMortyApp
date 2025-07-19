package com.jorgila.rickandmortyapp.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jorgila.rickandmortyapp.data.remote.ApiService
import com.jorgila.rickandmortyapp.data.remote.response.CharacterResponse
import com.jorgila.rickandmortyapp.domain.model.CharacterModel
import kotlinx.io.IOException

class CharactersPagingSource(private val api: ApiService) : PagingSource<Int, CharacterModel>() {

    override fun getRefreshKey(state: PagingState<Int, CharacterModel>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterModel> {

        return try {

            val page = params.key ?: 1
            val response = api.getAllCharacters(page)
            val characters = response.results

            val prev = if(page>0) -1 else null
            val next = if(response.info.next != null) page+1 else null

            LoadResult.Page(
                data = characters.map { characterResponse ->
                    characterResponse.toDomain()
                },
                prevKey = prev,
                nextKey = next
            )


        } catch (e: IOException) {
            LoadResult.Error(e)
        }
    }

}