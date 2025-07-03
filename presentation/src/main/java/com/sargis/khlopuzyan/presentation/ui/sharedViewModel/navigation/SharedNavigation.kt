package com.sargis.khlopuzyan.presentation.ui.sharedViewModel.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.sargis.khlopuzyan.presentation.ui.main.navigation.MainScreen
import com.sargis.khlopuzyan.presentation.ui.sharedViewModel.ui.Screen1
import com.sargis.khlopuzyan.presentation.ui.sharedViewModel.ui.Screen2
import com.sargis.khlopuzyan.presentation.ui.sharedViewModel.ui.SharedViewModel

fun NavGraphBuilder.SharedNavigation(navController: NavHostController) {
    navigation(
        startDestination = SharedScreen.Screen1.route,
        route = MainScreen.SharedViewModelScreen.route
    ) {
        composable(route = SharedScreen.Screen1.route) { entry ->
            val viewModel = entry.sharedViewModel<SharedViewModel>(navController)
            val state = viewModel.sharedState.collectAsStateWithLifecycle()
            Screen1(
                sharedState = state,
                updateCallback = {
                    viewModel.updateState(it)
                    navController.navigate(SharedScreen.Screen2.route)
                }
            )
        }
        composable(route = SharedScreen.Screen2.route) { entry ->
            val viewModel = entry.sharedViewModel<SharedViewModel>(navController)
            val state = viewModel.sharedState.collectAsStateWithLifecycle()
            Screen2(
                sharedState = state,
                updateCallback = {
                    viewModel.updateState(it)
                    navController.navigate(SharedScreen.Screen1.route)
                }
            )
        }
    }

//    composable(route = SharedScreen.Screen3.route) { entry ->
//        Screen3()
//    }
}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(
    navController: NavHostController,
): T {
    val navGraphRoute = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntry)
}