package com.kryptopass.cinematix.ui.compose.movie.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.kryptopass.cinematix.R
import com.kryptopass.common.state.CommonScreen
import kotlinx.coroutines.flow.collectLatest

@Composable
fun MovieListScreen(
    viewModel: MovieListViewModel,
    navController: NavController
) {
    // NOTE: only execute once, keep from recomposing/re-executing same block
    // can also be used to ensure that we do not trigger multiple data loads
    LaunchedEffect(Unit) {
        viewModel.submitAction(MovieListUiAction.Load)
    }

    viewModel.uiStateFlow.collectAsState().value.let { state ->
        CommonScreen(state = state) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                MovieList(it) { item ->
                    viewModel.submitAction(
                        MovieListUiAction.OnMovieItemClick(
                            item.movieId
                        )
                    )
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.singleEventFlow.collectLatest {
            when (it) {
                is MovieListUiSingleEvent.OpenDetailsScreen -> {
                    navController.navigate(it.navRoute)
                }
            }
        }
    }
}

@Composable
fun MovieList(
    model: MovieListModel,
    onItemClick: (MovieListItemModel) -> Unit,
) {
    Box {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier.fillMaxSize(), // Fill the entire Box
            content = {
                items(model.items.size) { index ->
                    val baseImagePath = "https://image.tmdb.org/t/p/w185/"
                    val posterPath = model.items[index].posterPath ?: ""
                    val imageUrl = "$baseImagePath$posterPath"

                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .clickable { onItemClick(model.items[index]) },
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(imageUrl),
                            contentDescription = stringResource(R.string.image_desc),
                            modifier = Modifier
                                .height(270.dp)
                                .fillMaxWidth(),
                            contentScale = ContentScale.Crop
                        )
                        //title
                    }
                }
            }
        )
    }
}