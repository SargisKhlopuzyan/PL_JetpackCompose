package com.sargis.khlopuzyan.presentation.ui.supportMultiScreenSizes.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sargis.khlopuzyan.presentation.ui.main.navigation.MainScreen
import com.sargis.khlopuzyan.presentation.ui.supportMultiScreenSizes.SupportMultiScreenSizesItemScreen
import com.sargis.khlopuzyan.presentation.ui.supportMultiScreenSizes.SupportMultiScreenSizesProfileScreen

/**
 * https://www.youtube.com/watch?v=0xtrtRstrLA&ab_channel=AhmedGuedmioui
 * */
fun NavGraphBuilder.supportMultiScreenSizesGraph(navController: NavController) {
    navigation(
        startDestination = SupportMultiScreenSizesScreen.SupportMultiScreenSizesItemScreen.route,
        route = MainScreen.SupportMultiScreenSizes.route
    ) {
        composable(route = SupportMultiScreenSizesScreen.SupportMultiScreenSizesProfileScreen.route) {
            SupportMultiScreenSizesProfileScreen(navController)
        }
        composable(
            route = SupportMultiScreenSizesScreen.SupportMultiScreenSizesItemScreen.route,
        ) {
            SupportMultiScreenSizesItemScreen(navController)
        }
    }
}