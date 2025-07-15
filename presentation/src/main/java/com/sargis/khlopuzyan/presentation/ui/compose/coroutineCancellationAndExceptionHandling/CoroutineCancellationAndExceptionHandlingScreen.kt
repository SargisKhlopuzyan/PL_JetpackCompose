package com.sargis.khlopuzyan.presentation.ui.compose.coroutineCancellationAndExceptionHandling

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

@Composable
fun CoroutineCancellationAndExceptionHandlingScreen() {
    val viewModel: CoroutineCancellationAndExceptionHandlingViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Scaffold { contentPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .padding(horizontal = 8.dp),
            overscrollEffect = null,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            item {
                Button(
                    modifier = Modifier.padding(bottom = 4.dp),
                    onClick = {
                        viewModel.launchACoroutinesInLaunchBlock()
                    }
                ) {
                    Text("Launch a coroutines in launch block * crash")
                }
            }

            item {
                Button(
                    modifier = Modifier.padding(bottom = 4.dp),
                    onClick = {
                        viewModel.launchACoroutineInLaunchBlockWithParentHandler()
                    }
                ) {
                    Text("Launch a coroutine in launch block with PARENT HANDLER")
                }
            }

            item {
                Button(
                    modifier = Modifier.padding(bottom = 4.dp),
                    onClick = {
                        viewModel.launchACoroutineInLaunchBlockWithChildHandler()
                    }
                ) {
                    Text("Launch a coroutine in launch block with CHILD HANDLER * crash")
                }
            }

            // Launch an async in launch block

            item {
                Button(
                    modifier = Modifier.padding(bottom = 4.dp),
                    onClick = {
                        viewModel.launchAnAsyncInLaunchBlock()
                    }
                ) {
                    Text("Launch an async in launch block * crash")
                }
            }

            item {
                Button(
                    modifier = Modifier.padding(bottom = 4.dp),
                    onClick = {
                        viewModel.launchAnAsyncInLaunchBlockWithAwait()
                    }
                ) {
                    Text("Launch an async in launch block with AWAIT * crash")
                }
            }

            item {
                Button(
                    modifier = Modifier.padding(bottom = 4.dp),
                    onClick = {
                        viewModel.launchAnAsyncInLaunchBlockWithParentHandler()
                    }
                ) {
                    Text("Launch an async in launch block with PARENT HANDLER")
                }
            }

            item {
                Button(
                    modifier = Modifier.padding(bottom = 4.dp),
                    onClick = {
                        viewModel.launchAnAsyncInLaunchBlockWithChildHandler()
                    }
                ) {
                    Text("Launch an async in launch block with CHILD HANDLER * crash")
                }
            }

            item {
                Button(
                    modifier = Modifier.padding(bottom = 4.dp),
                    onClick = {
                        viewModel.launchAnAsyncInLaunchBlockWithParentHandlerAndAwait()
                    }
                ) {
                    Text("Launch an async in launch block with PARENT HANDLER and AWAIT")
                }
            }

            item {
                Button(
                    modifier = Modifier.padding(bottom = 4.dp),
                    onClick = {
                        viewModel.launchAnAsyncInLaunchBlockWithChildHandlerAndAwait()
                    }
                ) {
                    Text("Launch an async in launch block with CHILD HANDLER and AWAIT * crash")
                }
            }

//             Launch an async in async block

            item {
                Button(
                    modifier = Modifier.padding(bottom = 4.dp),
                    onClick = {
                        viewModel.launchAnAsyncInAsyncBlock()
                    }
                ) {
                    Text("Launch an async in async block")
                }
            }

            item {
                Button(
                    modifier = Modifier.padding(bottom = 4.dp),
                    onClick = {
                        viewModel.launchAnAsyncInAsyncBlockWithAwait()
                    }
                ) {
                    Text("Launch an async in async block with AWAIT")
                }
            }

            item {
                Button(
                    modifier = Modifier.padding(bottom = 4.dp),
                    onClick = {
                        viewModel.launchAnAsyncInAsyncBlockWithParentHandler()
                    }
                ) {
                    Text("Launch an async in async block with PARENT HANDLER")
                }
            }

            item {
                Button(
                    modifier = Modifier.padding(bottom = 4.dp),
                    onClick = {
                        viewModel.launchAnAsyncInAsyncBlockWithChildHandler()
                    }
                ) {
                    Text("Launch an async in async block with CHILD HANDLER")
                }
            }

            item {
                Button(
                    modifier = Modifier.padding(bottom = 4.dp),
                    onClick = {
                        viewModel.launchAnAsyncInAsyncBlockWithParentHandlerAndAwait()
                    }
                ) {
                    Text("Launch an async in async block with PARENT HANDLER and AWAIT")
                }
            }

            item {
                Button(
                    modifier = Modifier.padding(bottom = 4.dp),
                    onClick = {
                        viewModel.launchAnAsyncInAsyncBlockWithChildHandlerAndAwait()
                    }
                ) {
                    Text("Launch an async in async block with CHILD HANDLER and AWAIT")
                }
            }

            item {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp)
                        .background(Color.LightGray),
                    text = uiState.catchableText
                )
            }

            item {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp)
                        .background(Color.LightGray),
                    text = uiState.printText
                )
            }
        }
    }
}