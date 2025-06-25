package com.sargis.khlopuzyan.presentation.ui.main.navigation

sealed class MainScreen(val route: String) {
    object LazyGridScreen : MainScreen("lazy_grid_screen")
    object ProfileScreen : MainScreen("profile_screen")
    object BtmNavMainScreen : MainScreen("btm_nav_main_screen")
    object DeeplinkScreen : MainScreen("deeplink_screen")
    object ShoppingScreen : MainScreen("shopping_screen")
    object Uri : MainScreen("uri")
    object SwipeableTabRows : MainScreen("swipeable_tab_rows")
    object SupportMultiScreenSizes : MainScreen("support_multi_screen_sizes")
}

fun getMainScreens() = listOf<MainScreen>(
    MainScreen.LazyGridScreen,
    MainScreen.ProfileScreen,
    MainScreen.BtmNavMainScreen,
    MainScreen.DeeplinkScreen,
    MainScreen.ShoppingScreen,
    MainScreen.Uri,
    MainScreen.SwipeableTabRows,
    MainScreen.SupportMultiScreenSizes,
)
