package com.sargis.khlopuzyan.presentation.ui.profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.sargis.khlopuzyan.presentation.ui.profile.ProfileDetailScreen
import com.sargis.khlopuzyan.presentation.ui.profile.ProfileScreen

fun NavGraphBuilder.profileGraph(navController: NavController) {
    navigation(
        startDestination = ProfileScreens.ProfileMainScreen.route,
        route = ProfileScreens.ProfileScreen.route
    ) {
        composable(route = ProfileScreens.ProfileMainScreen.route) {
            ProfileScreen(navController)
        }
        composable(
            route = ProfileScreens.DetailScreen.route + "/{name}", // "/{name}/{age}"/{...} or + "" OR route = Screen.DetailScreen.route + "?name = {name}",
            arguments = listOf(navArgument("name") {
                type = NavType.StringType
                defaultValue = ""
                nullable = true
            })
        ) { backStackEntry ->
            ProfileDetailScreen(navController, name = backStackEntry.arguments?.getString("name"))
        }
    }
}