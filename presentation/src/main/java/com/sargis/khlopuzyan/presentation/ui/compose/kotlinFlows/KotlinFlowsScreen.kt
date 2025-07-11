package com.sargis.khlopuzyan.presentation.ui.compose.kotlinFlows

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel

@Composable
fun KotlinFlowsScreen(
    navController: NavController,
    viewModel: KotlinFlowsViewModel = koinViewModel(),
) {
    KotlinFlows(
        collectFlowCallback = {
            viewModel.collectFlow()
        },
        collectLatestFlowCallback = {
            viewModel.collectLatestFlow()
        },
        collectFilteredFlowCallback = {
            viewModel.collectFilteredFlow()
        },
        collectFlowWithTerminalCountOperator = {
            viewModel.collectFlowWithTerminalCountOperator()
        },
        collectFlowWithTerminalReduceAndFoldOperators = {
            viewModel.collectFlowWithTerminalReduceAndFoldOperators()
        },
        collectFlowWithTerminalFlatOperator = {
            viewModel.collectFlowWithTerminalFlatOperator()
        },
        collectFlowWithTerminalBufferOperator = {
            viewModel.collectFlowWithTerminalBufferOperator()
        },
        collectFlowWithTerminalConflateOperator = {
            viewModel.collectFlowWithTerminalConflateOperator()
        },
        collectLatestFlowV2 = {
            viewModel.collectLatestFlowV2()
        },
    )
}

@Composable
fun KotlinFlows(
    collectFlowCallback: () -> Unit,
    collectLatestFlowCallback: () -> Unit,
    collectFilteredFlowCallback: () -> Unit,
    collectFlowWithTerminalCountOperator: () -> Unit,
    collectFlowWithTerminalReduceAndFoldOperators: () -> Unit,
    collectFlowWithTerminalFlatOperator: () -> Unit,
    collectFlowWithTerminalBufferOperator: () -> Unit,
    collectFlowWithTerminalConflateOperator: () -> Unit,
    collectLatestFlowV2: () -> Unit,
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .padding(16.dp),
        ) {
            Button(
                onClick = {
                    collectFlowCallback()
                }
            ) {
                Text(text = "Collect flow")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    collectLatestFlowCallback()
                }
            ) {
                Text(text = "Collect latest flow")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    collectFilteredFlowCallback()
                }
            ) {
                Text(text = "Collect Filtered flow")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    collectFlowWithTerminalCountOperator()
                }
            ) {
                Text(text = "Collect flow with terminal Count operator")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    collectFlowWithTerminalReduceAndFoldOperators()
                }
            ) {
                Text(text = "Collect flow with terminal Reduce and fold operators")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    collectFlowWithTerminalFlatOperator()
                }
            ) {
                Text(text = "Collect flow with terminal Flat operator")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    collectFlowWithTerminalBufferOperator()
                }
            ) {
                Text(text = "Collect flow with terminal Buffer operator")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    collectFlowWithTerminalConflateOperator()
                }
            ) {
                Text(text = "Collect flow with terminal Conflate operator")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    collectLatestFlowV2()
                }
            ) {
                Text(text = "Collect Latest flow v2")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun KotlinFlowsPreview() {
    KotlinFlows(
        {},
        {},
        {},
        {},
        {},
        {},
        {},
        {},
        {}
    )
}