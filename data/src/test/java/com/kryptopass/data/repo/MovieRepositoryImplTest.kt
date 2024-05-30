package com.kryptopass.data.repo

import com.kryptopass.data.repo.local.LocalMovieDataSource
import com.kryptopass.data.repo.remote.RemoteMovieDataSource
import com.kryptopass.domain.entity.Movie
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.kotlin.whenever

class MovieRepositoryImplTest {

    private val remoteSource = mock<RemoteMovieDataSource>()
    private val localSource = mock<LocalMovieDataSource>()
    private val repo = MovieRepositoryImpl(remoteSource, localSource)

    @Test
    fun testGetMovies() = runTest {
        val movies = listOf(
            Movie(
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
            ),
            Movie(
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
        )

        whenever(remoteSource.getMovies()).thenReturn(flowOf(movies))
        val result = repo.getMovies().first()

        assertEquals(movies, result)
        verify(localSource).addMovies(movies)
    }

    @Test
    fun testGetMovie() = runTest {
        val movieId = 1
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

        whenever(remoteSource.getMovie(movieId)).thenReturn(flowOf(movie))
        val result = repo.getMovie(movieId).first()

        assertEquals(movie, result)
        verify(localSource).addMovies(listOf(movie))
    }
}