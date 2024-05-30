package com.kryptopass.data.remote.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

// NOTE: not as secure as needed as this is an academic app...
private const val API_KEY = "de3474303f6356a8e5716cb340724bfa"

interface MovieService {

    @GET("movie/popular")
    suspend fun getMovieModel(
        @Query("api_key") apiKey: String = API_KEY
        // @Query("page") page: Int
    ): MovieModel

    @GET("movie/{movieId}")
    suspend fun getMovie(
        @Path("movieId") movieId: Int?,
        @Query("api_key") apiKey: String = API_KEY
    ): MovieItem
}