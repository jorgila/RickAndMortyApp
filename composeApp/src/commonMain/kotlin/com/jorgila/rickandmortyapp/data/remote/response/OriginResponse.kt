package com.jorgila.rickandmortyapp.data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class OriginResponse (
    val name: String
)