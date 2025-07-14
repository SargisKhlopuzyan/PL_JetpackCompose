package com.sargis.khlopuzyan.presentation.ui.compose.kotlinHotFlowsVsColdFlows

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import org.koin.androidx.compose.koinViewModel

val locationPermissions = arrayOf(
    Manifest.permission.ACCESS_FINE_LOCATION,
    Manifest.permission.ACCESS_COARSE_LOCATION
)

// https://www.youtube.com/watch?v=M8YtV47kaqA&ab_channel=PhilippLackner
@Composable
fun KotlinHotFlowsVsColdFlowsScreen(
    navController: NavController,
    viewModel: KotlinHotFlowsVsColdFlowsViewModel = koinViewModel(),
) {
    val isLocationPermissionsGrantedState =
        viewModel.locationPermissionStateFlow.collectAsStateWithLifecycle()
    val locationState = viewModel.locationStateFlow.collectAsStateWithLifecycle()

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { permissions ->
            viewModel.checkLocationPermission()
            viewModel.observeLocationFlow()
        }
    )

    LaunchedEffect(key1 = true) {
        if (!isLocationPermissionsGrantedState.value) {
            launcher.launch(locationPermissions)
        }
    }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {

            val scope = rememberCoroutineScope()
            // Converts flow to SharedFlow
//            val flow = remember {
//                viewModel.observeLocationFlow().shareIn(
//                    scope,
//                    SharingStarted.Eagerly
//                )
//            }

            // Converts flow to StateFlow
            val flow = remember {
                viewModel.observeLocationFlow().stateIn(
                    scope,
                    SharingStarted.Eagerly,
                    initialValue = null
                )
            }

            val location1 by flow.collectAsStateWithLifecycle(initialValue = null)
            val location2 by flow.collectAsStateWithLifecycle(initialValue = null)
//
            LaunchedEffect(location1) {
                println("LOG_TAG -> Location update: (${location1?.latitude}, ${location1?.longitude})")
            }

            LaunchedEffect(location2) {
                println("LOG_TAG -> Location update: (${location2?.latitude}, ${location2?.longitude})")
            }

            Button(onClick = {
                if (!isLocationPermissionsGrantedState.value) {
                    launcher.launch(locationPermissions)
                }
            }) {
                Text("Location Permissions ${isLocationPermissionsGrantedState.value}")
            }

            Button(onClick = {
                if (viewModel.locationPermissionStateFlow.value) {
                    viewModel.observeLocation()
                }
            }) {
                Text("Observe Location ${locationState.value}")
            }
        }
    }
}