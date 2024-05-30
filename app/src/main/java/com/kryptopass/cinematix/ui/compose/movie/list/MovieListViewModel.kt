package com.kryptopass.cinematix.ui.compose.movie.list

import androidx.lifecycle.viewModelScope
import com.kryptopass.common.nav.MovieInput
import com.kryptopass.common.nav.NavRoutes
import com.kryptopass.common.state.MviViewModel
import com.kryptopass.common.state.UiState
import com.kryptopass.domain.usecase.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val useCase: GetMoviesUseCase,
    private val converter: MovieListConverter
): MviViewModel<MovieListModel, UiState<MovieListModel>, MovieListUiAction, MovieListUiSingleEvent>() {

    override fun initState(): UiState<MovieListModel> = UiState.Loading

    override fun handleAction(action: MovieListUiAction) {
        when (action) {
            is MovieListUiAction.Load -> {
                loadMovies()
            }

            is MovieListUiAction.OnMovieItemClick -> {
                submitSingleEvent(
                    MovieListUiSingleEvent.OpenDetailsScreen(
                        NavRoutes.Movie.routeForMovie(
                            MovieInput(action.movieId, action.title, action.backdropPath)
                        )
                    )
                )
            }
        }
    }

    private fun loadMovies() {
        viewModelScope.launch {
            useCase.execute(GetMoviesUseCase.Request)
                .map {
                    converter.convert(it)
                }
                .collect {
                    submitState(it)
                }
        }
    }
}