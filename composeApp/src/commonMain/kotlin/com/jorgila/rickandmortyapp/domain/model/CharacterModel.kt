package com.jorgila.rickandmortyapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class CharacterModel(
    val id: Int,
    val isAlive: Boolean,
    val image: String,
    val name: String,
    val species: String
)
