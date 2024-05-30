package com.kryptopass.common.nav

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class NavRoutes(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList()
) {
    data object Movies : NavRoutes(ROUTE_MOVIES)

    data object Movie : NavRoutes(
        route = "movies/{$ARG_MOVIE_ID}?${ARG_MOVIE_TITLE}={${ARG_MOVIE_TITLE}}&${ARG_BACKDROP_PATH}={${ARG_BACKDROP_PATH}}&${ARG_OVERVIEW}={${ARG_OVERVIEW}}&${ARG_RELEASE_DATE}={${ARG_RELEASE_DATE}}",
        arguments = listOf(
            navArgument(ARG_MOVIE_ID) { type = NavType.IntType },
            navArgument(ARG_MOVIE_TITLE) { type = NavType.StringType },
            navArgument(ARG_BACKDROP_PATH) { type = NavType.StringType},
            navArgument(ARG_OVERVIEW) { type = NavType.StringType },
            navArgument(ARG_RELEASE_DATE) { type = NavType.StringType }
        )
    ) {
        fun routeForMovie(input: MovieInput) =
            "movies/${input.movieId}?${ARG_MOVIE_TITLE}=${input.title}&${ARG_BACKDROP_PATH}=${input.backdropPath}&${ARG_OVERVIEW}=${input.overview}&${ARG_RELEASE_DATE}=${input.releaseDate}"
        fun fromEntry(entry: NavBackStackEntry): MovieInput {
            return MovieInput(
                entry.arguments?.getInt(ARG_MOVIE_ID) ?: 0,
                entry.arguments?.getString(ARG_MOVIE_TITLE) ?: "",
                entry.arguments?.getString(ARG_BACKDROP_PATH) ?: "",
                entry.arguments?.getString(ARG_OVERVIEW) ?: "",
                entry.arguments?.getString(ARG_RELEASE_DATE) ?: ""
            )
        }
    }

    data object Search : NavRoutes(ROUTE_SEARCH)
    data object Login : NavRoutes(ROUTE_LOGIN)
    data object SignUp : NavRoutes(ROUTE_SIGN_UP)

    companion object {
        const val ROUTE_MOVIES = "movies"
        const val ROUTE_MOVIE = "movies/%s"
        const val ROUTE_SEARCH = "search"
        const val ARG_MOVIE_ID = "movieId"
        const val ARG_MOVIE_TITLE = "title"
        const val ARG_BACKDROP_PATH = "backdropPath"
        const val ARG_RELEASE_DATE = "releaseDate"
        const val ARG_OVERVIEW = "overview"
        const val ROUTE_LOGIN = "login"
        const val ROUTE_SIGN_UP = "signup"
    }
}