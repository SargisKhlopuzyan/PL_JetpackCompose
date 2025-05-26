package com.sargis.khlopuzyan.presentation.navigation.profile_navigation

sealed class Screen(val route: String) {
    object ProfileScreen : Screen("profile_screen")
    object DetailScreen : Screen("detail_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}