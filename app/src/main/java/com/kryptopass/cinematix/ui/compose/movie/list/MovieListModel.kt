package com.kryptopass.cinematix.ui.compose.movie.list

data class MovieListModel(
    val items: List<MovieListItemModel> = listOf()

    // TODO: other state we want to track
    // i.e. PagingState
)

data class MovieListItemModel(
    val adult: Boolean? = null,
    val backdropPath: String? = null,
    val genreIds: List<Int?>? = null,
    val movieId: Int? = null,
    val originalLanguage: String? = null,
    val originalTitle: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val posterPath: String? = null,
    val releaseDate: String? = null,
    val title: String? = null,
    val video: Boolean? = null,
    val voteAverage: Double? = null,
    val voteCount: Int? = null
)