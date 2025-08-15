package com.jorgila.rickandmortyapp.ui.detail

import com.jorgila.rickandmortyapp.domain.model.CharacterModel
import com.jorgila.rickandmortyapp.domain.model.EpisodeModel

data class CharacterDetailState (
    val characterModel: CharacterModel,
    val episodes: List<EpisodeModel>? = null
)