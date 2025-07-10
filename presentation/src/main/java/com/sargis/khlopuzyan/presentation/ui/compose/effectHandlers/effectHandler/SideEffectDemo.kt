package com.sargis.khlopuzyan.presentation.ui.compose.effectHandlers.effectHandler

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect

@Composable
fun SideEffectDemo(
    nonComposeCounter: Int,
) {
    /**
     * Called after every successful recomposition
     * */
    SideEffect {
        println("Called after every successful recomposition")
    }
}