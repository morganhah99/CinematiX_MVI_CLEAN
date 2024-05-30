package com.kryptopass.data.local.source

import com.kryptopass.data.local.db.MovieDao
import com.kryptopass.data.local.db.MovieEntity
import com.kryptopass.data.repo.local.LocalMovieDataSource
import com.kryptopass.domain.entity.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalMovieDataSourceImpl @Inject constructor(
    private val dao: MovieDao
) : LocalMovieDataSource {

    override fun getMovies(): Flow<List<Movie>> =
        dao.getMovies().map {
            it.map { movie ->
                Movie(
                    adult = movie.adult,
                    backdropPath = movie.backdropPath,
                    genreIds = movie.genreIds,
                    movieId = movie.movieId,
                    originalLanguage = movie.originalLanguage,
                    originalTitle = movie.originalTitle,
                    overview = movie.overview,
                    popularity = movie.popularity,
                    posterPath = movie.posterPath,
                    releaseDate = movie.releaseDate,
                    title = movie.title,
                    video = movie.video,
                    voteAverage = movie.voteAverage,
                    voteCount = movie.voteCount
                )
            }
        }

    override fun getMovie(movieId: Int?): Flow<Movie> =
        dao.getMovie(movieId).map {
            Movie(
                it.adult,
                it.backdropPath,
                it.genreIds,
                it.movieId,
                it.originalLanguage,
                it.originalTitle,
                it.overview,
                it.popularity,
                it.posterPath,
                it.releaseDate,
                it.title,
                it.video,
                it.voteAverage,
                it.voteCount
            )
        }

    override suspend fun addMovies(movies: List<Movie?>?) =
        dao.insertMovies(movies!!.map { movie ->
            MovieEntity(
                null,
                adult = movie?.adult,
                backdropPath = movie?.backdropPath,
                genreIds = movie?.genreIds,
                movieId = movie?.movieId,
                originalLanguage = movie?.originalLanguage,
                originalTitle = movie?.originalTitle,
                overview = movie?.overview,
                popularity = movie?.popularity,
                posterPath = movie?.posterPath,
                releaseDate = movie?.releaseDate,
                title = movie?.title,
                video = movie?.video,
                voteAverage = movie?.voteAverage,
                voteCount = movie?.voteCount
            )
        })
}