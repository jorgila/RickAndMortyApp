package com.jorgila.rickandmortyapp.ui.core.navigation.bottomNavigation.tabs.episodes

import app.cash.paging.PagingData
import com.jorgila.rickandmortyapp.domain.model.EpisodeModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class EpisodesState (
    val episodes: Flow<PagingData<EpisodeModel>> = emptyFlow(),
    val playVideo: String = ""
)