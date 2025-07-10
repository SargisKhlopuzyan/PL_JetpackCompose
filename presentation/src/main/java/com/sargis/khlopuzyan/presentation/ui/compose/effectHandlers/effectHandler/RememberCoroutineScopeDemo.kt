package com.sargis.khlopuzyan.presentation.ui.compose.effectHandlers.effectHandler

import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun RememberCoroutineScopeDemo() {
    /**
     *  This is also Effect handler
     * */
    val scope = rememberCoroutineScope()

    Button(
        onClick = {
            scope.launch {
                delay(1000)
                println("Hello world!")
            }
        }
    ) {

    }
}