package com.jorgila.rickandmortyapp.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jorgila.rickandmortyapp.domain.model.CharacterModel
import com.jorgila.rickandmortyapp.domain.model.CharacterOfTheDayModel
import kotlinx.serialization.json.Json

@Entity(tableName = "characterOfTheDay")
data class CharacterOfTheDayEntity(
    @PrimaryKey
    val id: Int,
    val isAlive: Boolean,
    val image: String,
    val name: String,
    val selectedDay: String,
    val species: String,
    val gender: String,
    val origin: String,
    val episodes: String
) {
    fun toDomain(): CharacterOfTheDayModel? {
        return CharacterOfTheDayModel(
            characterModel = CharacterModel(
                id = id,
                isAlive = isAlive,
                image = image,
                name = name,
                species = species,
                gender = gender,
                origin = origin,
                episodes = Json.decodeFromString<List<String>>(episodes)
            ),
            selectedDay = selectedDay
        )
    }
}