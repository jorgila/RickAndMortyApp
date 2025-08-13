package com.jorgila.rickandmortyapp.data.remote.response

import com.jorgila.rickandmortyapp.domain.model.CharacterModel
import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponse (
    val id : Int,
    val status: String,
    val image: String,
    val name: String,
    val species: String
){
    fun toDomain() : CharacterModel {
        return CharacterModel(
            id = id,
            image = image,
            name = name,
            isAlive = status.lowercase() == "alive",
            species = species
        )
    }
}