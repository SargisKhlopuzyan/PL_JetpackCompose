package com.sargis.khlopuzyan.presentation.ui.multipleBackStacksScreen.navigation

sealed class MBSBtmNavScreen(val route: String) {
    object HomeScreen : MBSBtmNavScreen("mbs_btm_nav_home_screen")
    object ChatScreen : MBSBtmNavScreen("mbs_btm_nav_chat_screen")
    object SettingsScreen : MBSBtmNavScreen("mbs_btm_nav_settings_screen")
}