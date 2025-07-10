package com.sargis.khlopuzyan.presentation.ui.compose.effectHandlers.effectHandler

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.mapNotNull

@Composable
fun SnapshotFlowDemo() {
//    val scaffoldState = rememberScaffoldState()

    val snackbarHostState = remember {
        SnackbarHostState()
    }

    LaunchedEffect(
        key1 = snackbarHostState
    ) {
        // snapshotFlow is the opposite function of collectAsState
        // it takes compose state and convert/construct it to flow, which emits values
        // whenever state changes
        snapshotFlow {
            snackbarHostState
        }.mapNotNull {
            it.currentSnackbarData?.visuals?.message
        }.distinctUntilChanged().collect { message ->
            println("A snackbar with message $message was shown")
        }
    }
}