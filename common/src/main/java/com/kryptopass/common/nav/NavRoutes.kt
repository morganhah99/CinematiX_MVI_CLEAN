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
        route = String.format(ROUTE_MOVIE, "{$ARG_MOVIE_ID}"),
        arguments = listOf(navArgument(ARG_MOVIE_ID) {
            type = NavType.IntType
        })
    ) {
        fun routeForMovie(input: MovieInput) = String.format(ROUTE_MOVIE, input.movieId)

        fun fromEntry(entry: NavBackStackEntry): MovieInput {
            return MovieInput(entry.arguments?.getInt(ARG_MOVIE_ID) ?: 0)
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
        const val ROUTE_LOGIN = "login"
        const val ROUTE_SIGN_UP = "signup"
    }
}