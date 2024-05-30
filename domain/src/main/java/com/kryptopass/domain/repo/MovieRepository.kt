package com.kryptopass.domain.repo

import com.kryptopass.domain.entity.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getMovies(): Flow<List<Movie?>?>

    fun getMovie(movieId: Int?): Flow<Movie>
}