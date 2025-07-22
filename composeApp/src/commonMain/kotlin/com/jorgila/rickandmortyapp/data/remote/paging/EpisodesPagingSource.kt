package com.jorgila.rickandmortyapp.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jorgila.rickandmortyapp.data.remote.ApiService
import com.jorgila.rickandmortyapp.domain.model.EpisodeModel

class EpisodesPagingSource( private val api: ApiService) : PagingSource<Int, EpisodeModel>() {
    override fun getRefreshKey(state: PagingState<Int, EpisodeModel>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, EpisodeModel> {
        return try {
            val page = params.key ?: 1
            val response = api.getAllEpisodes(page)
            val episodes = response.result
            val prev = if(page>1) page-1 else null
            val next = if(response.info.next !=null ) page+1 else null
            LoadResult.Page(
                data = episodes.map { it.toDomain() },
                prevKey = prev,
                nextKey = next
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}