package com.sargis.khlopuzyan.presentation.ui.navigation.main

sealed class MainScreen(val route: String) {
    object LazyGridScreen : MainScreen("lazy_grid_screen")
    object ProfileScreen : MainScreen("profile_screen")
    object BtmNavMainScreen : MainScreen("btm_nav_main_screen")
    object DeeplinkScreen : MainScreen("deeplink_screen")
    object ShoppingScreen : MainScreen("shopping_screen")
}