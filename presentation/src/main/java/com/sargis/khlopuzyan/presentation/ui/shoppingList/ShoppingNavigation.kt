package com.sargis.khlopuzyan.presentation.ui.shoppingList

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sargis.khlopuzyan.presentation.ui.shoppingList.add.ShoppingListAddScreen
import com.sargis.khlopuzyan.presentation.ui.shoppingList.detail.ShoppingListDetailScreen
import com.sargis.khlopuzyan.presentation.ui.shoppingList.home.ShoppingListScreen
import com.sargis.khlopuzyan.presentation.ui.shoppingList.imageSearch.ImageSearchScreen

@Composable
fun ShoppingNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = ShoppingListScreens.ShoppingListScreen.route
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