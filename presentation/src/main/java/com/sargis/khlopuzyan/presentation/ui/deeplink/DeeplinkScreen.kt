package com.sargis.khlopuzyan.presentation.ui.deeplink

sealed class DeeplinkScreen(val route: String) {
    object HomeScreen : DeeplinkScreen("deeplink_home_screen")
    object DetailScreen : DeeplinkScreen("deeplink_detail_screen")
}