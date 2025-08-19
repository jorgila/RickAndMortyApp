package com.jorgila.rickandmortyapp.ui.core.navigation.bottomNavigation.tabs.characters

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import app.cash.paging.compose.LazyPagingItems
import app.cash.paging.compose.collectAsLazyPagingItems
import coil3.compose.AsyncImage
import com.jorgila.rickandmortyapp.domain.model.CharacterModel
import com.jorgila.rickandmortyapp.ui.core.BackgroundPrimaryColor
import com.jorgila.rickandmortyapp.ui.core.Green
import com.jorgila.rickandmortyapp.ui.core.components.TextTitle
import com.jorgila.rickandmortyapp.ui.core.ex.vertical
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import rickandmortyapp.composeapp.generated.resources.Res
import rickandmortyapp.composeapp.generated.resources.rickface

@Composable
fun CharactersScreen(
    navigateToDetail: (CharacterModel) -> Unit
){
    val charactersViewModel = koinViewModel<CharactersViewModel>()
    val state by charactersViewModel.state.collectAsState()
    val characters = state.characters.collectAsLazyPagingItems()
    CharactersGridList(characters, state, navigateToDetail = { characterModel -> navigateToDetail(characterModel) })
}

@Composable
fun CharacterOfTheDay(characterModel: CharacterModel? = null){
    Card(
        modifier = Modifier.fillMaxWidth().height(400.dp),
        shape = RoundedCornerShape(12)
    ) {
        if(characterModel == null) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ){
                CircularProgressIndicator(
                    color = Green
                )
            }
        } else {
            Box(contentAlignment = Alignment.BottomStart){
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Green.copy(alpha = 0.5f))
                )
                AsyncImage(
                    model = characterModel.image,
                    contentDescription = "Character of the day",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Box(Modifier.fillMaxSize().background(
                    Brush.horizontalGradient(
                        0f to Color.Black.copy(alpha = 0.9f),
                        0.4f to Color.White.copy(alpha = 0f)
                    )
                ))
                Text(
                    characterModel.name.toString(),
                    fontSize = 40.sp,
                    maxLines = 1,
                    minLines = 1,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(horizontal = 24.dp, vertical = 16.dp)
                        .fillMaxHeight()
                        .vertical()
                        .rotate(-90f)
                )

            }
        }
    }
}

@Composable
fun CharactersGridList(
    characters: LazyPagingItems<CharacterModel>,
    state: CharactersState,
    navigateToDetail: (CharacterModel) -> Unit
) {
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundPrimaryColor)
            .padding(horizontal = 16.dp),
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){

        item(
            span = { GridItemSpan(2) }
        ) {
            Column {
                TextTitle("Characters")
                Spacer(modifier = Modifier.height(6.dp))
                CharacterOfTheDay(state.characterOfTheDay)
            }
        }


        when{
            characters.loadState.refresh is LoadState.Loading && characters.itemCount == 0 -> {
                // Initial load
                item(span = { GridItemSpan(2) } ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ){
                        CircularProgressIndicator(
                            modifier = Modifier.size(64.dp),
                            color = Green
                        )
                    }
                }
            }
            characters.loadState.refresh is LoadState.NotLoading && characters.itemCount == 0 -> {
                item {
                    // empty API
                    Text("No hay personajes :(")
                }
            }
            else -> {
                // Items Load
                items(characters.itemCount){ position ->
                    characters[position]?.let { characterModel ->
                        CharacterItemList(
                            characterModel = characterModel,
                            onItemSelected = { characterModel ->
                                navigateToDetail(characterModel)
                            }
                        )
                    }
                }
                if(characters.loadState.append is LoadState.Loading){
                    item(span = { GridItemSpan(2) } ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                            ,
                            contentAlignment = Alignment.Center
                        ){
                            CircularProgressIndicator(
                                modifier = Modifier.size(64.dp),
                                color = Green
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CharacterItemList(
    characterModel: CharacterModel,
    onItemSelected: (CharacterModel) -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(24))
            .border(
                width = 2.dp,
                color = Green,
                shape = RoundedCornerShape(0,24,0,24)
            )
            .fillMaxWidth()
            .height(150.dp)
            .clickable { onItemSelected(characterModel) },
        contentAlignment = Alignment.BottomCenter
    ){
        AsyncImage(
            model = characterModel.image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
            placeholder = painterResource(Res.drawable.rickface)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            Color.Black.copy(0f),
                            Color.Black.copy(0.6f),
                            Color.Black.copy(1f)
                        )
                    )
                ),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = characterModel.name,
                color = Color.White,
                fontSize = 18.sp
            )
        }
    }
}
