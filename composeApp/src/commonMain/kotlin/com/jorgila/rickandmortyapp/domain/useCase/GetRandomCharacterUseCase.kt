package com.jorgila.rickandmortyapp.domain.useCase

import com.jorgila.rickandmortyapp.domain.model.CharacterModel
import com.jorgila.rickandmortyapp.domain.model.CharacterOfTheDayModel
import com.jorgila.rickandmortyapp.domain.repository.Repository
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

class GetRandomCharacterUseCase(private val repository: Repository) {
    suspend operator fun invoke() : CharacterModel {

        val characterOfTheDay = repository.getCharacterDB()
        val selectedDay = getCurrentDayOfTheYear()
        return if ( characterOfTheDay != null && characterOfTheDay.selectedDay == selectedDay){
            characterOfTheDay.characterModel
        } else {
            val result = generateRandomCharacter()
            repository.saveCharacterDB(CharacterOfTheDayModel(
                characterModel = result,
                selectedDay = selectedDay
            ))
            result
        }
    }

    private suspend fun generateRandomCharacter() : CharacterModel {
        val random : Int = (1 .. 826).random()
        return repository.getSingleCharacter(random.toString())
    }

    @OptIn(ExperimentalTime::class)
    private fun getCurrentDayOfTheYear() : String {
        val instant = Clock.System.now()
        val localTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
        return "${localTime.dayOfYear}${localTime.year}"
    }

}