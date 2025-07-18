package com.sargis.khlopuzyan.presentation.ui.multipleBackStacksScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sargis.khlopuzyan.presentation.ui.main.navigation.MainScreen
import com.sargis.khlopuzyan.presentation.ui.multipleBackStacksScreen.navigation.MBSBtmNavScreen

// https://www.youtube.com/watch?v=fp1-YSmdzh8&ab_channel=PhilippLackner
@Composable
fun MultipleBackStacksScreen(/*rootNavController: NavHostController*/) {

    val rootNavController = rememberNavController()
    val navBackStackEntry by rootNavController.currentBackStackEntryAsState()

    val tabItems = listOf<BottomNavigationItem>(
        BottomNavigationItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            route = MBSBtmNavScreen.HomeScreen.route
        ),
        BottomNavigationItem(
            title = "Chat",
            selectedIcon = Icons.Filled.Email,
            unselectedIcon = Icons.Outlined.Email,
            route = MBSBtmNavScreen.ChatScreen.route
        ),

        BottomNavigationItem(
            title = "Settings",
            selectedIcon = Icons.Filled.Settings,
            unselectedIcon = Icons.Outlined.Settings,
            route = MBSBtmNavScreen.SettingsScreen.route
        )
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                tabItems.forEach { item ->
                    val isSelected = item.route == navBackStackEntry?.destination?.route
                    NavigationBarItem(
                        selected = isSelected,
                        label = {
                            Text(text = item.title)
                        },
                        icon = {
                            Icon(
                                imageVector = if (isSelected)
                                    item.selectedIcon
                                else
                                    item.unselectedIcon,
                                contentDescription = null
                            )
                        },
                        onClick = {
                            rootNavController.navigate(item.route) {
                                popUpTo(rootNavController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->

        NavHost(
            navController = rootNavController,
            startDestination = MBSBtmNavScreen.HomeScreen.route
        ) {
            composable(MBSBtmNavScreen.HomeScreen.route) {
                HomeNavHost()
            }
            composable(MBSBtmNavScreen.ChatScreen.route) {
                ChatNavHost()
            }
            composable(MBSBtmNavScreen.SettingsScreen.route) {
                SettingsNavHost()
            }
        }
    }
}

@Composable
fun HomeNavHost() {
    val homeNavController = rememberNavController()
    NavHost(homeNavController, startDestination = "${MBSBtmNavScreen.HomeScreen.route}1") {
        val homeRoute = MBSBtmNavScreen.HomeScreen.route
        for (i in 1..10) {
            composable("$homeRoute$i") {
                GenericScreen(
                    text = "Home $i",
                    onNextClick = {
                        if (i < 10) {
                            homeNavController.navigate("$homeRoute${(i + 1)}")
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun ChatNavHost() {
    val chatNavController = rememberNavController()
    NavHost(chatNavController, startDestination = "${MBSBtmNavScreen.ChatScreen.route}1") {
        val chatRoute = MBSBtmNavScreen.ChatScreen.route
        for (i in 1..10) {
            composable("$chatRoute$i") {
                GenericScreen(
                    text = "Chat $i",
                    onNextClick = {
                        if (i < 10) {
                            chatNavController.navigate("$chatRoute${(i + 1)}")
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun SettingsNavHost() {
    val settingsNavController = rememberNavController()
    NavHost(settingsNavController, startDestination = "${MBSBtmNavScreen.SettingsScreen.route}1") {
        val settingsRoute = MBSBtmNavScreen.SettingsScreen.route
        for (i in 0..10) {
            composable("$settingsRoute$i") {
                GenericScreen(
                    text = "Settings $i",
                    onNextClick = {
                        if (i < 10) {
                            settingsNavController.navigate("$settingsRoute${(i + 1)}")
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun GenericScreen(
    text: String,
    onNextClick: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = text)
        Spacer(Modifier.height(16.dp))

        Button(onClick = onNextClick) {
            Text("Next")
        }
    }
}

data class BottomNavigationItem(
    val title: String,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector,
    val route: String,
)

@Preview(showBackground = true)
@Composable
fun MultipleBackStacksScreenPreview() {
    MultipleBackStacksScreen(/*rememberNavController()*/)
}