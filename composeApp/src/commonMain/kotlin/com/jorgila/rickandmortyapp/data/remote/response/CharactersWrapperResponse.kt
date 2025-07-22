package com.jorgila.rickandmortyapp.data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class CharactersWrapperResponse(
    val info: InfoResponse,
    val results: List<CharacterResponse>
)
