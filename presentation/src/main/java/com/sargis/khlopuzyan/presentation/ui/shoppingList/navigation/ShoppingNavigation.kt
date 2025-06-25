package com.sargis.khlopuzyan.presentation.ui.shoppingList.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.sargis.khlopuzyan.presentation.ui.main.navigation.MainScreen
import com.sargis.khlopuzyan.presentation.ui.shoppingList.add.ShoppingListAddScreen
import com.sargis.khlopuzyan.presentation.ui.shoppingList.detail.ShoppingListDetailScreen
import com.sargis.khlopuzyan.presentation.ui.shoppingList.home.ShoppingListScreen
import com.sargis.khlopuzyan.presentation.ui.shoppingList.imageSearch.ImageSearchScreen

fun NavGraphBuilder.shoppingListGraph(navController: NavController) {
    navigation(
        startDestination = ShoppingListScreens.ShoppingListScreen.route,
        route = MainScreen.ShoppingScreen.route
    ) {
        composable(route = ShoppingListScreens.ShoppingListScreen.route) {
            ShoppingListScreen(navController)
        }
        composable(
            route = ShoppingListScreens.ShoppingListAddScreen.route,
        ) {
            ShoppingListAddScreen(navController)
        }
        composable(
            route = ShoppingListScreens.ShoppingListDetailScreen.route + "/{shoppingListItemId}",
            arguments = listOf(navArgument("shoppingListItemId") {
                type = NavType.StringType
                defaultValue = ""
                nullable = false
            })
        ) { backStackEntry ->
            val shoppingListItemId =
                backStackEntry.arguments?.getString("shoppingListItemId")?.toLong()
            ShoppingListDetailScreen(navController, shoppingListItemId!!)
        }
        composable(route = ShoppingListScreens.ShoppingListImageSearchScreen.route) {
            ImageSearchScreen(navController)
        }
    }
}