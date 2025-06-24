package com.sargis.khlopuzyan.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sargis.khlopuzyan.presentation.ui.bottomNavigation.BtmNavMainScreen
import com.sargis.khlopuzyan.presentation.ui.deeplink.DeeplinkNavigation
import com.sargis.khlopuzyan.presentation.ui.lazy_grid.LazyGridScreen
import com.sargis.khlopuzyan.presentation.ui.profileNavigation.ProfileNavigation
import com.sargis.khlopuzyan.presentation.ui.shoppingList.shoppingListGraph

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
        composable(route = MainScreen.ProfileScreen.route) {
            ProfileNavigation()
        }
        composable(route = MainScreen.BtmNavMainScreen.route) {
            BtmNavMainScreen()
        }
        composable(route = MainScreen.DeeplinkScreen.route) {
            DeeplinkNavigation()
        }
//        composable(route = MainScreen.ShoppingScreen.route) {
//            ShoppingNavigation()
//        }
        shoppingListGraph(navController)
    }
}
