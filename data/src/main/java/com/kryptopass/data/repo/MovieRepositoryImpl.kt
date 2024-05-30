package com.kryptopass.data.repo

import com.kryptopass.data.repo.local.LocalMovieDataSource
import com.kryptopass.data.repo.remote.RemoteMovieDataSource
import com.kryptopass.domain.entity.Movie
import com.kryptopass.domain.repo.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

class MovieRepositoryImpl(
    private val remoteSource: RemoteMovieDataSource,
    private val localSource: LocalMovieDataSource
): MovieRepository {

    override fun getMovies(): Flow<List<Movie?>?> =
        remoteSource.getMovies().onEach {
            localSource.addMovies(it)
        }

    override fun getMovie(movieId: Int?): Flow<Movie> =
        remoteSource.getMovie(movieId).onEach{
            localSource.addMovies(listOf(it))
        }
}