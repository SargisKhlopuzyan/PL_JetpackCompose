package com.sargis.khlopuzyan.presentation.navigation.profile_navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun ProfileNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.ProfileScreen.route) {
        composable(route = Screen.ProfileScreen.route) {
            ProfileScreen(navController)
        }
        composable(
            route = Screen.DetailScreen.route + "/{name}", // "/{name}/{age}"/{...} or + "" OR route = Screen.DetailScreen.route + "?name = {name}",
            arguments = listOf(navArgument("name") {
                type = NavType.StringType
                defaultValue = ""
                nullable = true
            })
        ) { entry ->
            ProfileDetailScreen(name = entry.arguments?.getString("name"))
        }
    }
}