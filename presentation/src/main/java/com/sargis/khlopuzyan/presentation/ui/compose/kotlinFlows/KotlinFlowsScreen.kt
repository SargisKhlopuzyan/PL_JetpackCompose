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
    )
}

@Composable
fun KotlinFlows(
    collectFlowCallback: () -> Unit,
    collectLatestFlowCallback: () -> Unit,
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
        }
    }
}

@Preview(showBackground = true)
@Composable
fun KotlinFlowsPreview() {
    KotlinFlows({}, {})
}