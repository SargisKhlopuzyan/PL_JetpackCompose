package com.sargis.khlopuzyan.presentation.ui.compose.composeStateTest

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel

@Composable
fun ComposeStateTestScreen(
    navController: NavController,
    viewModel: ComposeStateTestScreenViewModel = koinViewModel(),
) {
    println("LOG_TAG -> MAIN -> ComposeStateTestScreen")

    val testUiState = viewModel.uiState.collectAsStateWithLifecycle()
    val state = testUiState.value

    Scaffold { contentPadding ->
        ComposeStateTest(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .padding(16.dp),
            state,
            onItemChanged = { index, testUiState ->
                viewModel.onTestUiStateChanged(index, testUiState)
            }
        )
    }

}

@Composable
fun ComposeStateTest(
    modifier: Modifier = Modifier,
    testUiStates: List<TestUiState>,
    onItemChanged: (Int, TestUiState) -> Unit,
) {
    println("LOG_TAG -> CHILD -> ComposeStateTest")

    Column(modifier = modifier) {
        testUiStates.forEachIndexed { index, testUiState ->

//            val textState = mutableStateOf("")

            var textState by remember {
                mutableStateOf(testUiState.text)
            }

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                value = textState,
                onValueChange = {
                    textState = it
                },
                label = {
                    Text("Label")
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ComposeStateTestPreview() {
    val uiStates = mutableListOf(
        TestUiState("a"),
        TestUiState("b"),
        TestUiState("c"),
        TestUiState("d"),
        TestUiState("e"),
        TestUiState("f"),
    )
    ComposeStateTest(
        modifier = Modifier.fillMaxSize(),
        uiStates,
        onItemChanged = { index, testUiState -> })
}