package com.sargis.khlopuzyan.presentation.ui.profileNavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun ProfileNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ProfileScreens.ProfileScreen.route) {
        composable(route = ProfileScreens.ProfileScreen.route) {
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
            ProfileDetailScreen(name = backStackEntry.arguments?.getString("name"))
        }
    }
}