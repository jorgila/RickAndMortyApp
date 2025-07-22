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
            characters = characters.map { url -> url.substringAfter("/") },
            season = season,
            videoURL = getVideoUrlFromSeason(season)
        )
    }

    private fun getVideoUrlFromSeason(season: SeasonEpisode): String {
        return when (season){
            SeasonEpisode.SEASON_1 -> "https://youtu.be/E8cXKMR9a1Q?si=G0i6q8XDb126U4I5"
            SeasonEpisode.SEASON_2 -> "https://youtu.be/E8cXKMR9a1Q?si=G0i6q8XDb126U4I5"
            SeasonEpisode.SEASON_3 -> "https://youtu.be/E8cXKMR9a1Q?si=G0i6q8XDb126U4I5"
            SeasonEpisode.SEASON_4 -> "https://youtu.be/E8cXKMR9a1Q?si=G0i6q8XDb126U4I5"
            SeasonEpisode.SEASON_5 -> "https://youtu.be/E8cXKMR9a1Q?si=G0i6q8XDb126U4I5"
            SeasonEpisode.SEASON_6 -> "https://youtu.be/E8cXKMR9a1Q?si=G0i6q8XDb126U4I5"
            SeasonEpisode.SEASON_7 -> "https://youtu.be/E8cXKMR9a1Q?si=G0i6q8XDb126U4I5"
            SeasonEpisode.UNKNOWN -> "https://youtu.be/E8cXKMR9a1Q?si=G0i6q8XDb126U4I5"
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
