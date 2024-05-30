package com.kryptopass.data.remote.source

import com.kryptopass.data.remote.network.MovieItem
import com.kryptopass.data.remote.network.MovieService
import com.kryptopass.data.repo.remote.RemoteMovieDataSource
import com.kryptopass.domain.entity.Movie
import com.kryptopass.domain.entity.UseCaseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RemoteMovieDataSourceImpl @Inject constructor(
    private val service: MovieService
) : RemoteMovieDataSource {

    override fun getMovies(): Flow<List<Movie?>?> = flow {
        emit(service.getMovieModel())
    }.map { model ->
        model.results?.map { apiModel ->
            convert(apiModel)
        }
    }.catch {
        throw UseCaseException.MovieException(it)
    }

    override fun getMovie(movieId: Int?): Flow<Movie> = flow {
        emit(service.getMovie(movieId))
    }.map {
        convert(it)
    }.catch {
        throw UseCaseException.MovieException(it)
    }

    private fun convert(model: MovieItem?) =
        Movie(
            model?.adult, model?.backdropPath, model?.genreIds, model?.id, model?.originalLanguage,
            model?.originalTitle, model?.overview, model?.popularity, model?.posterPath,
            model?.releaseDate, model?.title, model?.video, model?.voteAverage, model?.voteCount
        )
}