package com.kryptopass.data.local

import com.kryptopass.data.local.db.MovieDao
import com.kryptopass.data.local.db.MovieEntity
import com.kryptopass.data.local.source.LocalMovieDataSourceImpl
import com.kryptopass.domain.entity.Movie
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class LocalMovieDataSourceImplTest {

    private val dao = mock<MovieDao>()
    private val dataSource = LocalMovieDataSourceImpl(dao)

    @Test
    fun testGetMovies() = runTest {
        val localMovies = listOf(
            MovieEntity(
                null,
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
            )
        )

        whenever(dao.getMovies()).thenReturn(flowOf(localMovies))
        val result = dataSource.getMovies().first()

        assertEquals(expectedMovies, result)
    }

    @Test
    fun testGetMovie() = runTest {
        val localMovie = MovieEntity(
            null,
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

        whenever(dao.getMovie(1)).thenReturn(flowOf(localMovie))
        val result = dataSource.getMovie(1)

        assertEquals(expectedMovie, result.first())
    }

    @Test
    fun testAddMovies() = runTest {
        val localMovies = listOf(
            MovieEntity(
                null,
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
        )
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
            )
        )

        dataSource.addMovies(movies)

        verify(dao).insertMovies(localMovies)
    }

}