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

class GetMoviesUseCaseTest {

    private val repo = mock<MovieRepository>()

    private val useCase = GetMoviesUseCase(
        mock(),
        repo
    )

    @Test
    fun testProcess() = runTest {
        val movie1 = Movie(
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
        val movie2 = Movie(
            adult = true,
            backdropPath = "backdropPath2",
            genreIds = listOf(4, 5, 6),
            movieId = 2,
            originalLanguage = "originalLanguage2",
            originalTitle = "originalTitle2",
            overview = "overview2",
            popularity = 2.0,
            posterPath = "posterPath2",
            releaseDate = "releaseDate2",
            title = "title2",
            video = true,
            voteAverage = 2.0,
            voteCount = 2
        )

        whenever(repo.getMovies()).thenReturn(flowOf(listOf(movie1, movie2)))
        val response = useCase.process(GetMoviesUseCase.Request).first()

        assertEquals(GetMoviesUseCase.Response(listOf(movie1, movie2)), response)
    }
}