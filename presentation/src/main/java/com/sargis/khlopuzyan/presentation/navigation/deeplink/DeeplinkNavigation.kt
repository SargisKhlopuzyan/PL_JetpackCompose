package com.sargis.khlopuzyan.presentation.navigation.deeplink

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink

@Composable
fun DeeplinkNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = DeeplinkScreen.HomeScreen.route
    ) {
        composable(route = DeeplinkScreen.HomeScreen.route) {
            DeeplinkHomeScreen(navController)
        }
        composable(
            route = DeeplinkScreen.DetailScreen.route,
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "https://pl-coding.com/{id}"
                    action = Intent.ACTION_VIEW
                }
            ),
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) { entry ->
            val id = entry.arguments?.getInt("id")
            DeeplinkDetailScreen(id)
        }
    }
}