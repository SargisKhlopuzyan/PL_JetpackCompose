package com.sargis.khlopuzyan.presentation.ui.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sargis.khlopuzyan.presentation.ui.biometric.BiometricScreen
import com.sargis.khlopuzyan.presentation.ui.bottomNavigation.BtmNavMainScreen
import com.sargis.khlopuzyan.presentation.ui.compose.navigation.composeTestScreen
import com.sargis.khlopuzyan.presentation.ui.deeplink.DeeplinkNavigation
import com.sargis.khlopuzyan.presentation.ui.main.lazy_grid.LazyGridScreen
import com.sargis.khlopuzyan.presentation.ui.messageQueue.MessageQueueScreen
import com.sargis.khlopuzyan.presentation.ui.multipleBackStacksScreen.MultipleBackStacksScreen
import com.sargis.khlopuzyan.presentation.ui.pl.performanceOptimizationsForJetpackComposeUi.PerformanceOptimizationsForJetpackComposeUi
import com.sargis.khlopuzyan.presentation.ui.pl.performanceOptimizationsForJetpackComposeUi.PerformanceOptimizationsForJetpackComposeUiViewModel
import com.sargis.khlopuzyan.presentation.ui.profile.navigation.profileGraph
import com.sargis.khlopuzyan.presentation.ui.pullToRefreshLazyColumn.PullToRefreshLazyColumnScreen
import com.sargis.khlopuzyan.presentation.ui.sharedViewModel.navigation.SharedNavigation
import com.sargis.khlopuzyan.presentation.ui.shoppingList.navigation.shoppingListGraph
import com.sargis.khlopuzyan.presentation.ui.supportMultiScreenSizes.navigation.supportMultiScreenSizesGraph
import com.sargis.khlopuzyan.presentation.ui.swipeableTabRows.SwipeableTabRowsScreen
import com.sargis.khlopuzyan.presentation.ui.uris.UriScreen
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MainScreen.LazyGridScreen.route
    ) {
        composable(route = MainScreen.LazyGridScreen.route) {
            LazyGridScreen(navController)
        }
        profileGraph(navController)
        composable(route = MainScreen.BtmNavMainScreen.route) {
            BtmNavMainScreen()
        }
        composable(route = MainScreen.DeeplinkScreen.route) {
            DeeplinkNavigation()
        }
        shoppingListGraph(navController)
        composable(route = MainScreen.Uri.route) {
            UriScreen()
        }
        composable(route = MainScreen.SwipeableTabRows.route) {
            SwipeableTabRowsScreen(navController)
        }
        supportMultiScreenSizesGraph(navController)

        SharedNavigation(navController)

        composable(route = MainScreen.MessageQueue.route) {
            MessageQueueScreen()
        }

        composeTestScreen(navController)

        composable(route = MainScreen.PullToRefreshLazyColumnScreen.route) {
            PullToRefreshLazyColumnScreen()
        }

        composable(route = MainScreen.BiometricScreen.route) {
            BiometricScreen()
        }

        composable(route = MainScreen.MultipleBackStacksScreen.route) {
            MultipleBackStacksScreen(/*navController*/)
        }

        composable(route = MainScreen.PerformanceOptimizationsForJetpackComposeUi.route) {
            val viewModel: PerformanceOptimizationsForJetpackComposeUiViewModel = koinViewModel()
            val color by viewModel.color.collectAsStateWithLifecycle()
            PerformanceOptimizationsForJetpackComposeUi(color, onColorClick = {
                viewModel.setColor(it)
            })
        }
    }
}