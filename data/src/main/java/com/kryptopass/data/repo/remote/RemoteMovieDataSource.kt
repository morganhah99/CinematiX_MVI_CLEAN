package com.kryptopass.data.repo.remote

import com.kryptopass.domain.entity.Movie
import kotlinx.coroutines.flow.Flow

interface RemoteMovieDataSource {

    fun getMovies(): Flow<List<Movie?>?>

    fun getMovie(movieId: Int?): Flow<Movie>
}