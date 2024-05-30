package com.kryptopass.domain.usecase

import com.kryptopass.domain.entity.Movie
import com.kryptopass.domain.repo.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetMovieByIdUseCase(
    configuration: Configuration,
    private val repo: MovieRepository
) : UseCase<GetMovieByIdUseCase.Request, GetMovieByIdUseCase.Response>(configuration) {

    override fun process(request: Request): Flow<Response> =
        repo.getMovie(request.movieId)
            .map {
                Response(it)
            }

    data class Request(val movieId: Int?) : UseCase.Request
    data class Response(val movie: Movie?) : UseCase.Response
}