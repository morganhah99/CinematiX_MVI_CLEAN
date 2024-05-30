package com.kryptopass.data.repo.local

import com.kryptopass.domain.entity.Movie
import kotlinx.coroutines.flow.Flow

interface LocalMovieDataSource {

    fun getMovies(): Flow<List<Movie>>
    fun getMovie(movieId: Int?): Flow<Movie>

    suspend fun addMovies(movies: List<Movie?>?)
}