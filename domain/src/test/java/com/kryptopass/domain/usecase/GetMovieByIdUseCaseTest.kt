package com.kryptopass.domain.usecase

import com.kryptopass.domain.entity.Movie
import com.kryptopass.domain.repo.MovieRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetMovieByIdUseCaseTest {

    private val repo = mock<MovieRepository>()
    private val useCase = GetMovieByIdUseCase(
        mock(),
        repo
    )

    @Test
    fun testProcess() = runTest {
        val request = GetMovieByIdUseCase.Request(1)
        val movie = Movie(
            adult = false,
            backdropPath = "backdropPath",
            genreIds = listOf(1, 2, 3),
            movieId = 1,
            originalLanguage = "originalLanguage",
            originalTitle = "originalTitle",
            overview = "overview",
            popularity = 1.0,
            posterPath = "posterPath",
            releaseDate = "releaseDate",
            title = "title",
            video = false,
            voteAverage = 1.0,
            voteCount = 1
        )

        whenever(repo.getMovie(request.movieId)).thenReturn(flowOf(movie))
        val response = useCase.process(request).first()

        assertEquals(GetMovieByIdUseCase.Response(movie), response)
    }
}