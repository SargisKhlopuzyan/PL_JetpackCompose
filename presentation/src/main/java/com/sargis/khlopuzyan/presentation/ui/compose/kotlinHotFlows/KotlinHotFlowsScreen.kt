package com.sargis.khlopuzyan.presentation.ui.compose.kotlinHotFlows

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun KotlinHotFlowsScreen(
    navController: NavController,
    viewModel: KotlinHotFlowsViewModel = koinViewModel(),
) {
    var count = viewModel.stateFlow.collectAsStateWithLifecycle(initialValue = 10) // prints 0

    var sharedFlowValue by rememberSaveable {
        mutableStateOf("")
    }

//    var sharedFlowValue =
//        viewModel.sharedFlow.collectAsStateWithLifecycle(initialValue = 10) // prints 0

    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(key1 = true) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.sharedFlow.collectLatest { number ->
                println("LOG_TAG -> LaunchedEffect -> Shared Flow collected $number")
            }
        }
    }

    KotlinHotFlows(
        count = count.value,
        sharedFlowValue = sharedFlowValue,
        incrementCounterStateFlow = {
            viewModel.incrementCounterStateFlow()
        },
        collectSharedFlow = {
            sharedFlowValue = ""
            viewModel.collectSharedFlow()
        },
    )
}

@Composable
fun KotlinHotFlows(
    count: Int,
    sharedFlowValue: String,
    incrementCounterStateFlow: () -> Unit,
    collectSharedFlow: () -> Unit,
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .padding(16.dp),
        ) {
            Text(text = "Counter: $count")

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    incrementCounterStateFlow()
                }
            ) {
                Text(text = "StateFlow / Increment counter")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Shared flow value: $sharedFlowValue")

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    collectSharedFlow()
                }
            ) {
                Text(text = "Collect SharedFlow value")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun KotlinHotFlowsPreview() {
    KotlinHotFlows(
        19,
        "19",
        {},
        {}
    )
}

suspend fun <T> LifecycleOwner.collectLatestLifecycleFlow(
    flow: Flow<T>,
    callback: suspend (T) -> Unit,
) {
    repeatOnLifecycle(Lifecycle.State.STARTED) {
        flow.collectLatest(callback)
    }
}

@Composable
fun <T> collectLatestLifecycleFlow(flow: Flow<T>, callback: suspend (T) -> Unit) {
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(true) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collectLatest(callback)
        }
    }
}

@Composable
fun <T> collectLifecycleFlow(flow: Flow<T>, callback: suspend (T) -> Unit) {
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(true) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collect(callback)
        }
    }
}