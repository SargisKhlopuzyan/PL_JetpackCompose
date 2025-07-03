package com.sargis.khlopuzyan.presentation.ui.sharedViewModel.navigation

sealed class SharedScreen(val route: String) {
    object Screen1 : SharedScreen("screen_shared_screen_1")
    object Screen2 : SharedScreen("screen_shared_screen_2")
    object Screen3 : SharedScreen("screen_shared_screen_3")
}