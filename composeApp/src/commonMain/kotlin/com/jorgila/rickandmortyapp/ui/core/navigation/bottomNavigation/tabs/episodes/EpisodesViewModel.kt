package com.jorgila.rickandmortyapp.ui.core.navigation.bottomNavigation.tabs.episodes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.jorgila.rickandmortyapp.domain.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class EpisodesViewModel(
    private val repository: Repository
) : ViewModel() {

    private val _state = MutableStateFlow<EpisodesState>(EpisodesState())
    val state: StateFlow<EpisodesState> = _state

    init {
        _state.update { state ->
            state.copy(
                episodes = repository.getAllEpisodes().cachedIn(viewModelScope)
            )
        }
    }

}