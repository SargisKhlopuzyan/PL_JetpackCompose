package com.sargis.khlopuzyan.presentation.ui.bottomNavigation

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun BottomNavigationBar(
    items: List<BtmNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BtmNavItem) -> Unit
) {
    val backStateEntry = navController.currentBackStackEntryAsState()

    NavigationBar(
        modifier = modifier,
        containerColor = Color.DarkGray,
        tonalElevation = 5.dp
    ) {
        items.forEach { item ->
            val selected = item.route == backStateEntry.value?.destination?.route
            NavigationBarItem(
                selected = selected,
                modifier = Modifier,
                colors = NavigationBarItemColors(
                    selectedIconColor = Color.Green,
                    selectedTextColor = Color.Green,
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray,
                    disabledIconColor = Color.Gray,
                    disabledTextColor = Color.Gray,
                    selectedIndicatorColor = Color.Transparent
                ),
                icon = {
                    Column(horizontalAlignment = CenterHorizontally) {
                        if (item.badgeCount > 0) {
                            BadgedBox(
                                badge = {
                                    Badge {
                                        Text(text = item.badgeCount.toString())
                                    }
                                }
                            ) {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.name
                                )
                            }
                        } else {
                            Icon(imageVector = item.icon, contentDescription = item.name)
                        }
                        if (selected) {
                            Text(
                                text = item.name,
                                textAlign = TextAlign.Center,
                                fontSize = 10.sp
                            )
                        }
                    }
                },
                onClick = {
                    onItemClick(item)
                }
            )
        }
    }
}

@Preview
@Composable
fun BottomNavigationBarPreview() {
    val items = listOf(
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
    )
    BottomNavigationBar(
        items = items,
        navController = rememberNavController(),
        modifier = Modifier,
        onItemClick = {

        }
    )
}