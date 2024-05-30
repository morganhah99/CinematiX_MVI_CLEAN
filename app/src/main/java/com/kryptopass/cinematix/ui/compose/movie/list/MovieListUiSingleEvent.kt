package com.kryptopass.cinematix.ui.compose.movie.list

import com.kryptopass.common.state.UiSingleEvent

// NOTE: concrete UiSingleEvent specific for Launch
sealed class MovieListUiSingleEvent : UiSingleEvent {

    data class OpenDetailsScreen(val navRoute: String) : MovieListUiSingleEvent()
}