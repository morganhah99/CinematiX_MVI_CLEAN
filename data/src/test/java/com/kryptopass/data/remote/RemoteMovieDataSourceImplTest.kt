package com.kryptopass.data.remote

import com.kryptopass.data.remote.network.MovieItem
import com.kryptopass.data.remote.network.MovieModel
import com.kryptopass.data.remote.network.MovieService
import com.kryptopass.data.remote.source.RemoteMovieDataSourceImpl
import com.kryptopass.domain.entity.Movie
import com.kryptopass.domain.entity.UseCaseException
import junit.framework.TestCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class RemoteMovieDataSourceImplTest {

    private val service = mock<MovieService>()
    private val dataSource = RemoteMovieDataSourceImpl(service)

    private val movieId = 1

    @Test
    fun testGetMovieModel() = runTest {
        val remoteModel = MovieModel (
            page = 1,
            results = listOf(
                MovieItem(
                    adult = false,
                    backdropPath = "backdropPath",
                    genreIds = listOf(1, 2, 3),
                    id = 1,
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
                MovieItem(
                    adult = true,
                    backdropPath = "backdropPath2",
                    genreIds = listOf(4, 5, 6),
                    id = 2,
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
            ),
            totalPages = 1,
            totalResults = 2
        )

        val expectedMovies = listOf(
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

        whenever(service.getMovieModel()).thenReturn(remoteModel)
        val result = dataSource.getMovies().first()

        assertEquals(expectedMovies, result)
    }

    @Test
    fun testGetMovie() = runTest {
        val remoteMovie = MovieItem(
            adult = false,
            backdropPath = "backdropPath",
            genreIds = listOf(1, 2, 3),
            id = 1,
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
        val expectedMovie = Movie(
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

        whenever(service.getMovie(movieId)).thenReturn(remoteMovie)
        val result = dataSource.getMovie(movieId).first()

        assertEquals(expectedMovie, result)
    }

    @Test
    fun testGetMoviesThrowsError() = runTest {
        whenever(service.getMovieModel()).thenThrow(RuntimeException())

        dataSource.getMovies().catch {
            TestCase.assertTrue(it is UseCaseException.MovieException)
        }
    }

    @Test
    fun testGetMovieThrowsError() = runTest {
        whenever(service.getMovie(movieId)).thenThrow(RuntimeException())

        dataSource.getMovie(movieId).catch {
            TestCase.assertTrue(it is UseCaseException.MovieException)
        }
    }
}