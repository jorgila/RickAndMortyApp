package com.jorgila.rickandmortyapp.ui.core.navigation.bottomNavigation.tabs.episodes

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import app.cash.paging.compose.collectAsLazyPagingItems
import com.jorgila.rickandmortyapp.domain.model.EpisodeModel
import com.jorgila.rickandmortyapp.domain.model.SeasonEpisode
import com.jorgila.rickandmortyapp.ui.core.BackgroundPrimaryColor
import com.jorgila.rickandmortyapp.ui.core.BackgroundSecondaryColor
import com.jorgila.rickandmortyapp.ui.core.BackgroundTertiaryColor
import com.jorgila.rickandmortyapp.ui.core.DefaultTextColor
import com.jorgila.rickandmortyapp.ui.core.PlaceholderColor
import com.jorgila.rickandmortyapp.ui.core.components.PagingLoadingState
import com.jorgila.rickandmortyapp.ui.core.components.PagingType
import com.jorgila.rickandmortyapp.ui.core.components.PagingWrapper
import com.jorgila.rickandmortyapp.ui.core.components.VideoPlayer
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import rickandmortyapp.composeapp.generated.resources.Res
import rickandmortyapp.composeapp.generated.resources.placeholder
import rickandmortyapp.composeapp.generated.resources.portal
import rickandmortyapp.composeapp.generated.resources.season1
import rickandmortyapp.composeapp.generated.resources.season2
import rickandmortyapp.composeapp.generated.resources.season3
import rickandmortyapp.composeapp.generated.resources.season4
import rickandmortyapp.composeapp.generated.resources.season5
import rickandmortyapp.composeapp.generated.resources.season6
import rickandmortyapp.composeapp.generated.resources.season7

@Composable
fun EpisodesScreen(){

    val episodesViewModel = koinViewModel<EpisodesViewModel>()

    val state by episodesViewModel.state.collectAsState()

    val episodes = state.episodes.collectAsLazyPagingItems()

    Column(modifier = Modifier
        .fillMaxSize()
        .background(BackgroundPrimaryColor)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        PagingWrapper(
            pagingType = PagingType.ROW,
            pagingItems = episodes,
            initialView = {
                PagingLoadingState()
            },
            itemView = { EpisodeItemList(it){ url ->
                episodesViewModel.onPlaySelected(url)
            } }
        )
        EpisodePlayer(
            playVideo = state.playVideo,
            onCloseVideo = {
                episodesViewModel.onCloseVideo()
            }

        )


    }
}

@Composable
fun EpisodePlayer(
    playVideo: String,
    onCloseVideo: () -> Unit
) {
    AnimatedContent(playVideo.isNotBlank()) { condition ->
        if (condition) {
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(16.dp)
                    .border(3.dp, Color.Green, CardDefaults.elevatedShape)
            ) {
                Box(modifier = Modifier.background(Color.Black)) {
                    Box(
                        modifier = Modifier
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        VideoPlayer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp),
                            url = playVideo
                        )
                    }
                    Row {
                        Spacer(
                            modifier = Modifier.weight(1f)
                        )
                        Image(
                            painter = painterResource(Res.drawable.portal),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(8.dp)
                                .size(40.dp)
                                .clickable {
                                    onCloseVideo()
                                }
                        )
                    }
                }
            }

        } else {
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = CardDefaults.elevatedCardColors(containerColor = PlaceholderColor)
            ) {
                Column {
                    Image(painter = painterResource(Res.drawable.placeholder), contentDescription = null)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Aw, jeez, you gotta click the video, guys! I mean, it might be important or something!", color = DefaultTextColor, fontStyle = FontStyle.Italic, modifier = Modifier.padding(16.dp))
                }
            }

        }
    }
}

@Composable
fun EpisodeItemList(
    episode: EpisodeModel,
    onEpisodeSelected: (String) -> Unit
){
    Column(
        modifier = Modifier
            .width(120.dp)
            .padding(horizontal = 8.dp)
            .clickable { onEpisodeSelected(episode.videoURL) }
    ) {
        Image(
            modifier = Modifier.height(180.dp).fillMaxWidth(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            painter = painterResource(getSeasonImage(episode.season))
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(episode.episode, color = DefaultTextColor, fontWeight = FontWeight.Bold)
    }
}

fun getSeasonImage(seasonEpisode: SeasonEpisode) : DrawableResource {
    return when(seasonEpisode){
        SeasonEpisode.SEASON_1 -> Res.drawable.season1
        SeasonEpisode.SEASON_2 -> Res.drawable.season2
        SeasonEpisode.SEASON_3 -> Res.drawable.season3
        SeasonEpisode.SEASON_4 -> Res.drawable.season4
        SeasonEpisode.SEASON_5 -> Res.drawable.season5
        SeasonEpisode.SEASON_6 -> Res.drawable.season6
        SeasonEpisode.SEASON_7 -> Res.drawable.season7
        SeasonEpisode.UNKNOWN -> Res.drawable.season1
    }
}