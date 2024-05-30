package com.kryptopass.cinematix.ui.compose.movie.list

import android.content.Context
import com.kryptopass.common.state.CommonResultConverter
import com.kryptopass.domain.usecase.GetMoviesUseCase
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MovieListConverter @Inject constructor(
    @ApplicationContext private val context: Context
) : CommonResultConverter<GetMoviesUseCase.Response, MovieListModel>() {

    override fun convertSuccess(
        data: GetMoviesUseCase.Response
    ): MovieListModel {
        return MovieListModel(
            items = data.movies!!.map {
                MovieListItemModel(
                    it?.adult,
                    it?.backdropPath,
                    it?.genreIds,
                    it?.movieId,
                    it?.originalLanguage,
                    it?.originalTitle,
                    it?.overview,
                    it?.popularity,
                    it?.posterPath,
                    it?.releaseDate,
                    it?.title,
                    it?.video,
                    it?.voteAverage,
                    it?.voteCount
                )
            }
        )
    }
}