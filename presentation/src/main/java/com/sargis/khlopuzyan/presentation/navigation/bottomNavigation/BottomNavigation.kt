package com.sargis.khlopuzyan.presentation.navigation.bottomNavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun BottomNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = BtmNavScreen.BtmNavHomeScreen.route) {
        composable(route = BtmNavScreen.BtmNavHomeScreen.route) {
            BtmNavHomeScreen()
        }
        composable(route = BtmNavScreen.BtmNavChatScreen.route) {
            BtmNavChatScreen()
        }
        composable(route = BtmNavScreen.BtmNavSettingsScreen.route) {
            BtmNavSettingsScreen()
        }
    }
}