package com.sargis.khlopuzyan.presentation.ui.compose.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sargis.khlopuzyan.presentation.ui.compose.JetpackComposeMainScreen
import com.sargis.khlopuzyan.presentation.ui.compose.colorBoxScreen.ColorBoxScreen
import com.sargis.khlopuzyan.presentation.ui.compose.composeStateTest.ComposeStateTestScreen
import com.sargis.khlopuzyan.presentation.ui.compose.effectHandlers.EffectHandlersScreen
import com.sargis.khlopuzyan.presentation.ui.compose.kotlinFlows.KotlinFlowsScreen
import com.sargis.khlopuzyan.presentation.ui.compose.kotlinHotFlows.KotlinHotFlowsScreen
import com.sargis.khlopuzyan.presentation.ui.compose.stylingText.StylingTextScreen
import com.sargis.khlopuzyan.presentation.ui.compose.textfieldsButtonsSnackbars.TextfieldsButtonsSnackbarsScreen
import com.sargis.khlopuzyan.presentation.ui.main.navigation.MainScreen

fun NavGraphBuilder.composeTestScreen(navController: NavController) {
    navigation(
        startDestination = ComposeScreen.JetpackComposeMainScreen.route,
        route = MainScreen.ComposeScreen.route
    ) {
        composable(
            route = ComposeScreen.JetpackComposeMainScreen.route
        ) {
            JetpackComposeMainScreen(navController)
        }
        composable(
            route = ComposeScreen.ComposeStateTestScreen.route
        ) {
            ComposeStateTestScreen(navController)
        }
        composable(
            route = ComposeScreen.ColorBoxScreen.route
        ) {
            ColorBoxScreen(navController)
        }
        composable(
            route = ComposeScreen.StylingTextScreen.route
        ) {
            StylingTextScreen()
        }
        composable(
            route = ComposeScreen.TextfieldsButtonsSnackbarsScreen.route
        ) {
            TextfieldsButtonsSnackbarsScreen()
        }
        composable(
            route = ComposeScreen.EffectHandlersScreen.route
        ) {
            EffectHandlersScreen()
        }
        composable(
            route = ComposeScreen.KotlinFlowsScreen.route
        ) {
            KotlinFlowsScreen(navController)
        }
        composable(
            route = ComposeScreen.KotlinHotFlowsScreen.route
        ) {
            KotlinHotFlowsScreen(navController)
        }
    }
}