package com.jorgila.rickandmortyapp.data.remote.response

import com.jorgila.rickandmortyapp.domain.model.CharacterModel
import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponse (
    val id : Int,
    val status: String,
    val image: String
){
    fun toDomain() : CharacterModel {
        return CharacterModel(
            id = id,
            image = image,
            isAlive = status.lowercase() == "alive"
        )
    }
}