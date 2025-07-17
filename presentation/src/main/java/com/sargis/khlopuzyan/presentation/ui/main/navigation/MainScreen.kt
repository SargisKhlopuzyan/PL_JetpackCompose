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
    object SharedViewModelScreen : MainScreen("sharedView_model_screen")
    object MessageQueue : MainScreen("message_queue")
    object ComposeScreen : MainScreen("compose_screen")
    object PullToRefreshLazyColumnScreen : MainScreen("pull_to_refresh_lazy_column_screen")
    object BiometricScreen : MainScreen("biometric_screen")
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
    MainScreen.SharedViewModelScreen,
    MainScreen.MessageQueue,
    MainScreen.ComposeScreen,
    MainScreen.PullToRefreshLazyColumnScreen,
    MainScreen.BiometricScreen,
)
