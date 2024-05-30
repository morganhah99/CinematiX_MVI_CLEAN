package com.kryptopass.cinematix.ui.compose.movie.list

import com.kryptopass.common.state.UiAction

// NOTE: concrete UiAction specific for Launch
sealed class MovieListUiAction : UiAction {

    data object Load : MovieListUiAction()
    data class OnMovieItemClick(val movieId: Int?) : MovieListUiAction()
}