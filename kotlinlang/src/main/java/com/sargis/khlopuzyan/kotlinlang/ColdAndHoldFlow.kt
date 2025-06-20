package com.sargis.khlopuzyan.kotlinlang

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Cold Flow: Values are emitted only when collected, and each collector gets a fresh sequence of values.
 * Hot Flow: Values are continuously emitted regardless of collectors, with new collectors receiving the latest values.
 * */
fun main() {
    val flowType = FlowType.Hot_Shared_Flow
    runBlocking {
        when (flowType) {
            FlowType.Cold_Flow -> {
                emitColdFlow()
//                emitColdFlow().collect {
//                    println("collecting value: $it")
//                }
            }

            FlowType.Hot_State_Flow -> {
                launch(Dispatchers.IO) {
                    emitHotFlow_StateFlow()
                }

                launch {
                    stateFlow.collect {
                        println("1 * stateFlow: $it")
                    }
                }

                launch {
//                  delay(5000)
                    delay(1000)
                    stateFlow.collect {
                        println("2 * stateFlow: $it")
                    }
                }
            }

            FlowType.Hot_Shared_Flow -> {
                launch(Dispatchers.IO) {
                    emitHotFlow_SharedFlow()
                }

                launch {
                    sharedFlow.collect {
                        println("1 * sharedFlow: $it")
                    }
                }

                launch {
//                  delay(5000)
                    delay(1000)
                    sharedFlow.collect {
                        println("2 * sharedFlow: $it")
                    }
                }
            }
        }
    }
}

private val stateFlow: MutableStateFlow<Int> = MutableStateFlow<Int>(0)
private val sharedFlow: MutableSharedFlow<Int> = MutableSharedFlow<Int>()

private fun emitColdFlow() = flow {
    for (i in 0..5) {
        delay(i * 100L)
        println("emitting value: ${i * 100}")
        emit(i * 100)
    }
}

private suspend fun emitHotFlow_StateFlow() {
    for (i in 0..5) {
        delay(i * 100L)
        println("emitting value: ${i * 100}")
        stateFlow.emit(i * 100)
    }
}

private suspend fun emitHotFlow_SharedFlow() {
    for (i in 0..5) {
        delay(i * 100L)
        println("emitting value: ${i * 100}")
        sharedFlow.emit(i * 100)
    }
}

enum class FlowType {
    Cold_Flow,
    Hot_State_Flow,
    Hot_Shared_Flow
}