package com.sargis.khlopuzyan.presentation.ui.bottomNavigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.sargis.khlopuzyan.presentation.ui.bottomNavigation.navigation.BottomNavigation
import com.sargis.khlopuzyan.presentation.ui.bottomNavigation.navigation.BtmNavScreen

@Composable
fun BtmNavMainScreen() {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.Companion.fillMaxSize(),
        bottomBar = {
            BottomNavigationBar(
                items = listOf(
                    BtmNavItem(
                        name = "Home",
                        icon = Icons.Default.Home,
                        route = BtmNavScreen.BtmNavHomeScreen.route
                    ),
                    BtmNavItem(
                        name = "Chat",
                        badgeCount = 1,
                        icon = Icons.Default.Notifications,
                        route = BtmNavScreen.BtmNavChatScreen.route
                    ),
                    BtmNavItem(
                        name = "Settings",
                        badgeCount = 23,
                        icon = Icons.Default.Settings,
                        route = BtmNavScreen.BtmNavSettingsScreen.route
                    ),
                ),
                navController = navController,
                modifier = Modifier.Companion,
                onItemClick = {
                    navController.navigate(it.route)
                }
            )
        }) { contentPadding ->
        BottomNavigation(navController)
    }
}