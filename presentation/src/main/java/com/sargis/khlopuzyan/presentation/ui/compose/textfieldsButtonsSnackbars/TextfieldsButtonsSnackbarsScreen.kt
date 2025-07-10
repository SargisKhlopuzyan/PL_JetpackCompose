package com.sargis.khlopuzyan.presentation.ui.compose.textfieldsButtonsSnackbars

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun TextfieldsButtonsSnackbarsScreen() {

    val snackbarHostState = remember {
        SnackbarHostState()
    }

    val scope = rememberCoroutineScope()

    var textFieldState by remember {
        mutableStateOf("")
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = {
            SnackbarHost(
                modifier = Modifier,
                hostState = snackbarHostState,
                snackbar = {
                    Snackbar(
//                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        action = {
                            Text(
                                modifier = Modifier
                                    .padding(end = 16.dp)
                                    .clickable {
                                        snackbarHostState.currentSnackbarData?.performAction()
                                    },
                                text = "Action"
                            )
                        },
                        dismissAction = {
                            Text(
                                modifier = Modifier
                                    .padding(end = 16.dp)
                                    .clickable {
                                        snackbarHostState.currentSnackbarData?.dismiss()
                                    },
                                text = "dismissAction"
                            )
                        }
                    ) {
                        Text(textFieldState)
                    }
                }
            )
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = textFieldState,
                onValueChange = { text ->
                    textFieldState = text
                },
                label = {
                    Text("Enter text")
                },
                singleLine = true
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = textFieldState,
                            duration = SnackbarDuration.Short
                        )
                    }
                }
            ) {
                Text(text = "Show Snackbar")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TextfieldsButtonsSnackbarsScreenPreview() {
    TextfieldsButtonsSnackbarsScreen()
}