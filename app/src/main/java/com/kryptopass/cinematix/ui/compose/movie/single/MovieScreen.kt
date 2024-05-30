package com.kryptopass.cinematix.ui.compose.movie.single

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.kryptopass.common.nav.MovieInput

@Composable
fun MovieScreen(
    viewModel: MovieViewModel,
    input: MovieInput
) {

    val baseImagePath = "https://image.tmdb.org/t/p/w185/"
    val fullImageUrl = baseImagePath + input.backdropPath


    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Image(
            painter = rememberAsyncImagePainter(fullImageUrl),
            contentDescription = null,
            modifier = Modifier
                .height(150.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )

        Text(
            text = "${input.title}",
            style = MaterialTheme.typography.headlineLarge
        )

    }
}