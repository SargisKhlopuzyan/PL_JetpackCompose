package com.sargis.khlopuzyan.presentation.ui.main.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sargis.khlopuzyan.presentation.ui.bottomNavigation.BtmNavMainScreen
import com.sargis.khlopuzyan.presentation.ui.deeplink.DeeplinkNavigation
import com.sargis.khlopuzyan.presentation.ui.main.lazy_grid.LazyGridScreen
import com.sargis.khlopuzyan.presentation.ui.profile.navigation.profileGraph
import com.sargis.khlopuzyan.presentation.ui.shoppingList.navigation.shoppingListGraph
import com.sargis.khlopuzyan.presentation.ui.uris.UriScreen

@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MainScreen.LazyGridScreen.route
    ) {
        composable(route = MainScreen.LazyGridScreen.route) {
            LazyGridScreen(navController)
        }
        profileGraph(navController)
        composable(route = MainScreen.BtmNavMainScreen.route) {
            BtmNavMainScreen()
        }
        composable(route = MainScreen.DeeplinkScreen.route) {
            DeeplinkNavigation()
        }
        shoppingListGraph(navController)
        composable(route = MainScreen.Uri.route) {
            UriScreen()
        }
    }
}
