package com.sargis.khlopuzyan.presentation.ui.navigation.bottomNavigation

import androidx.compose.ui.graphics.vector.ImageVector

data class BtmNavItem(
    val name: String,
    val icon: ImageVector,
    val badgeCount: Int = 0,
    val route: String
)