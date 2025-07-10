package com.sargis.khlopuzyan.presentation.ui.compose.kotlinFlows

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlin.random.Random

// https://www.youtube.com/watch?v=ZX8VsqNO_Ss&ab_channel=PhilippLackner
class KotlinFlowsViewModel : ViewModel() {

    val countDownFlow = flow<Int> {
        val startingValue = 10
        var currentValue = startingValue
        emit(startingValue)
        while (currentValue > 0) {
            delay(1000)
            currentValue--
            emit(currentValue)
        }
    }

    fun collectFlow() {
        viewModelScope.launch {
            countDownFlow.collect { time ->
                delay(Random.nextLong(15) * 100)
                println("LOG_TAG -> The current time is $time")
            }
        }
    }

    fun collectLatestFlow() {
        viewModelScope.launch {
            countDownFlow.collectLatest { time ->
                delay(Random.nextLong(15) * 100)
                println("LOG_TAG -> The current time is $time")
            }
        }
    }
}