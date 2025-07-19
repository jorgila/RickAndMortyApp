package com.jorgila.rickandmortyapp.domain.model

data class CharacterModel(
    val id: Int,
    val isAlive: Boolean,
    val image: String,
    val name: String
)
