package com.sargis.khlopuzyan.presentation.ui.compose.effectHandlers.effectHandler

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.produceState
import kotlinx.coroutines.delay

@Composable
fun ProduceStateDemo(countUpTo: Int): State<Int> {
    return produceState(initialValue = 0) {
        while (value < countUpTo) {
            delay(1000)
            value += 1
        }
    }

//    // This is the same as code below // produceState
//    return flow<Int> {
//        var value = 0
//        while (value < countUpTo) {
//            delay(1000)
//            value += 1
//            emit(value)
//        }
//    }.collectAsState(initial = 0)
}