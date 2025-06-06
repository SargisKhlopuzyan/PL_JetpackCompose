package com.sargis.khlopuzyan.presentation.ui.navigation.main

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sargis.khlopuzyan.presentation.ui.navigation.deeplink.DeeplinkNavigation
import com.sargis.khlopuzyan.presentation.ui.navigation.profile_navigation.ProfileNavigation
import com.sargis.khlopuzyan.presentation.ui.navigation.lazy_grid.LazyGridScreen
import com.sargis.khlopuzyan.presentation.ui.navigation.bottomNavigation.BtmNavMainScreen
import com.sargis.khlopuzyan.presentation.ui.shoppingList.ShoppingNavigation

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
        composable(route = MainScreen.ShoppingScreen.route) {
            ShoppingNavigation()
        }
    }
}