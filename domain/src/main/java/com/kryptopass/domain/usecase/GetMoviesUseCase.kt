package com.kryptopass.domain.usecase

import com.kryptopass.domain.entity.Movie
import com.kryptopass.domain.repo.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetMoviesUseCase(
    configuration: Configuration,
    private val repo: MovieRepository
) : UseCase<GetMoviesUseCase.Request, GetMoviesUseCase.Response>(configuration) {

    override fun process(request: Request): Flow<Response> =
        repo.getMovies()
            .map {
                Response(it)
            }

    data object Request : UseCase.Request
    data class Response(val movies: List<Movie?>?) : UseCase.Response
}