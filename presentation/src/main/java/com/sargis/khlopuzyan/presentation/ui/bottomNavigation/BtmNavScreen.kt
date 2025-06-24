package com.sargis.khlopuzyan.presentation.ui.bottomNavigation

sealed class BtmNavScreen(val route: String) {
    object BtmNavHomeScreen : BtmNavScreen("btm_nav_home_screen")
    object BtmNavChatScreen : BtmNavScreen("btm_nav_chat_screen")
    object BtmNavSettingsScreen : BtmNavScreen("btm_nav_settings_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                "/$arg"
            }
        }
    }
}