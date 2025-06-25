package com.sargis.khlopuzyan.presentation.ui.profile.navigation

sealed class ProfileScreens(val route: String) {
    object ProfileScreen : ProfileScreens("profile_screen")
    object ProfileMainScreen : ProfileScreens("profile_main_screen")
    object DetailScreen : ProfileScreens("detail_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}