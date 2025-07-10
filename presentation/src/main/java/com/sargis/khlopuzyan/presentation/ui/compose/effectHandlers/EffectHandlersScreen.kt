package com.sargis.khlopuzyan.presentation.ui.compose.effectHandlers

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

private var i = 0

@Composable
fun EffectHandlersScreen() {

    val launchedEffectViewModel = koinViewModel<LaunchedEffectViewModel>()

    LaunchedEffect(true) {
        launchedEffectViewModel.sharedFlow.collect { event ->
            when (event) {
                is ScreenEvents.Navigate -> {

                }

                is ScreenEvents.ShowSnackbar -> {

                }
            }
        }
    }

    EffectHandlers()
}

@Composable
fun EffectHandlers() {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { contentPadding ->

        var text by remember {
            mutableStateOf("")
        }

        // When key is changed, the LaunchedEffect scope will be canceled and launched again
        LaunchedEffect(key1 = text) {
            delay(2000)
            println("LOG_TAG -> Text is $text")
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .padding(16.dp)
        ) {


            Button(
                onClick = {
                    text += "#"
                }
            ) {
                i++
                Text(text = text)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EffectHandlersPreview() {
    EffectHandlers()
}