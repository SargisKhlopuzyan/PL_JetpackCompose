package com.sargis.khlopuzyan.presentation.ui.navigation.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.sargis.khlopuzyan.presentation.ui.navigation.bottomNavigation.BtmNavMainScreen
import com.sargis.khlopuzyan.presentation.ui.navigation.deeplink.DeeplinkNavigation
import com.sargis.khlopuzyan.presentation.ui.navigation.lazy_grid.LazyGridScreen
import com.sargis.khlopuzyan.presentation.ui.navigation.profile_navigation.ProfileNavigation
import com.sargis.khlopuzyan.presentation.ui.shoppingList.ShoppingListScreens
import com.sargis.khlopuzyan.presentation.ui.shoppingList.ShoppingNavigation
import com.sargis.khlopuzyan.presentation.ui.shoppingList.add.ShoppingListAddScreen
import com.sargis.khlopuzyan.presentation.ui.shoppingList.detail.ShoppingListDetailScreen
import com.sargis.khlopuzyan.presentation.ui.shoppingList.home.ShoppingListScreen
import com.sargis.khlopuzyan.presentation.ui.shoppingList.imageSearch.ImageSearchScreen

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

fun NavGraphBuilder.shoppingListGraph(navController: NavController) {
    navigation(
        startDestination = ShoppingListScreens.ShoppingListScreen.route,
        route = MainScreen.ShoppingScreen.route
    ) {
        composable(route = ShoppingListScreens.ShoppingListScreen.route) {
            ShoppingListScreen(navController)
        }
        composable(route = ShoppingListScreens.ShoppingListAddScreen.route) {
            ShoppingListAddScreen(navController)
        }
        composable(route = ShoppingListScreens.ShoppingListDetailScreen.route) {
            ShoppingListDetailScreen()
        }
        composable(route = ShoppingListScreens.ShoppingListImageSearchScreen.route) {
            ImageSearchScreen(navController = navController)
        }
    }
}