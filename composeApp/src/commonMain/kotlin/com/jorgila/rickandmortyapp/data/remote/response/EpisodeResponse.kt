package com.jorgila.rickandmortyapp.data.remote.response

import com.jorgila.rickandmortyapp.domain.model.EpisodeModel
import com.jorgila.rickandmortyapp.domain.model.SeasonEpisode
import kotlinx.serialization.Serializable

@Serializable
data class EpisodeResponse(
    val id: Int,
    val name: String,
    val episode: String,
    val characters: List<String>
) {
    fun toDomain() : EpisodeModel {
        val season = getSeasonFromEpisodeCode(episode)
        return EpisodeModel(
            id = id,
            name = name,
            episode = episode,
            characters = characters.map { url -> url.substringAfterLast("/") },
            season = season,
            videoURL = getVideoUrlFromSeason(season)
        )
    }

    private fun getVideoUrlFromSeason(season: SeasonEpisode): String {
        return when (season){
            SeasonEpisode.SEASON_1 -> "https://firebasestorage.googleapis.com/v0/b/rickandmortyapp-3c3e3.firebasestorage.app/o/Rick%20and%20Morty%20Season%201%20Promos%20-%20Starburns%20Industries%20(1080p%2C%20h264).mp4?alt=media&token=3c29ea11-a191-41ad-bdcb-fe1d1d21902e"
            SeasonEpisode.SEASON_2 -> "https://firebasestorage.googleapis.com/v0/b/rickandmortyapp-3c3e3.firebasestorage.app/o/Rick%20and%20Morty%20Season%201%20Promos%20-%20Starburns%20Industries%20(1080p%2C%20h264).mp4?alt=media&token=3c29ea11-a191-41ad-bdcb-fe1d1d21902e"
            SeasonEpisode.SEASON_3 -> "https://firebasestorage.googleapis.com/v0/b/rickandmortyapp-3c3e3.firebasestorage.app/o/Rick%20and%20Morty%20Season%201%20Promos%20-%20Starburns%20Industries%20(1080p%2C%20h264).mp4?alt=media&token=3c29ea11-a191-41ad-bdcb-fe1d1d21902e"
            SeasonEpisode.SEASON_4 -> "https://firebasestorage.googleapis.com/v0/b/rickandmortyapp-3c3e3.firebasestorage.app/o/Rick%20and%20Morty%20Season%201%20Promos%20-%20Starburns%20Industries%20(1080p%2C%20h264).mp4?alt=media&token=3c29ea11-a191-41ad-bdcb-fe1d1d21902e"
            SeasonEpisode.SEASON_5 -> "https://firebasestorage.googleapis.com/v0/b/rickandmortyapp-3c3e3.firebasestorage.app/o/Rick%20and%20Morty%20Season%201%20Promos%20-%20Starburns%20Industries%20(1080p%2C%20h264).mp4?alt=media&token=3c29ea11-a191-41ad-bdcb-fe1d1d21902e"
            SeasonEpisode.SEASON_6 -> "https://firebasestorage.googleapis.com/v0/b/rickandmortyapp-3c3e3.firebasestorage.app/o/Rick%20and%20Morty%20Season%201%20Promos%20-%20Starburns%20Industries%20(1080p%2C%20h264).mp4?alt=media&token=3c29ea11-a191-41ad-bdcb-fe1d1d21902e"
            SeasonEpisode.SEASON_7 -> "https://firebasestorage.googleapis.com/v0/b/rickandmortyapp-3c3e3.firebasestorage.app/o/Rick%20and%20Morty%20Season%201%20Promos%20-%20Starburns%20Industries%20(1080p%2C%20h264).mp4?alt=media&token=3c29ea11-a191-41ad-bdcb-fe1d1d21902e"
            SeasonEpisode.UNKNOWN -> "https://firebasestorage.googleapis.com/v0/b/rickandmortyapp-3c3e3.firebasestorage.app/o/Rick%20and%20Morty%20Season%201%20Promos%20-%20Starburns%20Industries%20(1080p%2C%20h264).mp4?alt=media&token=3c29ea11-a191-41ad-bdcb-fe1d1d21902e"
        }
    }

    private fun getSeasonFromEpisodeCode(episode: String) : SeasonEpisode {
        return when {
            episode.startsWith("S01") -> SeasonEpisode.SEASON_1
            episode.startsWith("S02") -> SeasonEpisode.SEASON_2
            episode.startsWith("S03") -> SeasonEpisode.SEASON_3
            episode.startsWith("S04") -> SeasonEpisode.SEASON_4
            episode.startsWith("S05") -> SeasonEpisode.SEASON_5
            episode.startsWith("S06") -> SeasonEpisode.SEASON_6
            episode.startsWith("S07") -> SeasonEpisode.SEASON_7
            else -> SeasonEpisode.UNKNOWN
        }
    }

}
