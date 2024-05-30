package com.kryptopass.cinematix.ui.compose.movie.single

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kryptopass.common.nav.MovieInput

@Composable
fun MovieScreen(
    viewModel: MovieViewModel,
    input: MovieInput
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Movie Details Screen",
            style = MaterialTheme.typography.headlineLarge
        )
    }
}