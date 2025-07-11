package com.sargis.khlopuzyan.presentation.ui.compose.kotlinFlows

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.fold
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.reduce
import kotlinx.coroutines.launch
import kotlin.random.Random

// https://www.youtube.com/watch?v=ZX8VsqNO_Ss&ab_channel=PhilippLackner
// https://www.youtube.com/watch?v=sk3svS_fzZM&ab_channel=PhilippLackner
class KotlinFlowsViewModel : ViewModel() {

    val countDownFlow = flow<Int> {
        val startingValue = 5
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

    fun collectFilteredFlow() {
        // The same as code below
//        countDownFlow.onEach { time ->
//            println("time: $time")
//        }.launchIn(viewModelScope)

        viewModelScope.launch {
            countDownFlow
                .filter {
                    it % 2 == 0
                }
                .map { time ->
                    time * time
                }
                .onEach { time ->
                    println("time: $time")
                }
                .collect { time ->
                    println("LOG_TAG -> The current time is $time")
                }
        }
    }

    fun collectFlowWithTerminalCountOperator() {
        viewModelScope.launch {
            val count = countDownFlow
                .filter {
                    it % 2 == 0
                }
                .map { time ->
                    time * time
                }
                .onEach { time ->
                    println("time: $time")
                }
                .count { time ->
                    time % 2 == 0
                }

            println("LOG_TAG -> The count is $count")
        }
    }

    fun collectFlowWithTerminalReduceAndFoldOperators() {
        viewModelScope.launch {
            val reduceResult = countDownFlow
                .reduce { accumulator, value ->
                    accumulator + value
                }

            println("LOG_TAG -> The reduceResult is $reduceResult")


            // The same as reduce just need initial value
            val reduceFold = countDownFlow
                .fold(initial = 100) { accumulator, value ->
                    accumulator + value
                }

            println("LOG_TAG -> The reduceFold is $reduceFold")
        }
    }

    // [[1, 2],[1, 2, 3]]
    // [1, 2, 1, 2, 3]
    // We can combine 2 flows in one
    fun collectFlowWithTerminalFlatOperator() {
        val flow1 = flow<Int> {
            emit(1)
            delay(500)
            emit(2)
        }

        viewModelScope.launch {
            flow1.flatMapConcat { value ->
                flow<Int> {
                    emit(value + 1)
                    delay(700)
                    emit(value + 2)
                }
            }.collect { value ->
                println("LOG_TAG -> The value is $value")
            }
        }
    }

    fun collectFlowWithTerminalFlatOperatorApiDemo() {
        val flow1 = (1..5).asFlow()
        viewModelScope.launch {
            flow1.flatMapConcat { id ->
                flow {
                    // getRecipeById(id)
                    emit(id * id)
                }
            }.collect { value ->
                println("LOG_TAG -> The value is $value")
            }
        }
    }

    fun collectFlowWithTerminalBufferOperator() {
        val flow = flow {
            delay(250)
            emit("Appetizer")
            delay(1000)
            emit("Main dish")
            delay(1000)
            emit("Dessert")
        }
        viewModelScope.launch {
            flow.onEach {
                println("LOG_TAG -> $it is delivered")
            }
                .buffer()
                // buffer makes collect flow and emitter flow run in different coroutine
                .collect {
                    println("LOG_TAG -> Now eating $it")
                    delay(1500)
                    println("LOG_TAG -> Finishing $it")
                }
        }
    }

    fun collectFlowWithTerminalConflateOperator() {
        val flow = flow {
            delay(250)
            emit("Appetizer")
            delay(1000)
            emit("Main dish")
            delay(1000)
            emit("Dessert")
        }
        viewModelScope.launch {
            flow.onEach {
                println("LOG_TAG -> $it is delivered")
            }
                // conflate works like buffer whit one difference, in case emitter emits value,
                // but collector still have values to collect, collector will skip not collected one
                // and will jump to last emitted values
                .conflate()
                // buffer makes collect flow and emitter flow run in different coroutine
                .collect {
                    println("LOG_TAG -> Now eating $it")
                    delay(1500)
                    println("LOG_TAG -> Finishing $it")
                }
        }
    }

    fun collectLatestFlowV2() {
        val flow = flow {
            delay(250)
            emit("Appetizer")
            delay(1000)
            emit("Main dish")
            delay(1000)
            emit("Dessert")
        }
        viewModelScope.launch {
            flow.onEach {
                println("LOG_TAG -> $it is delivered")
            }
                // collectLatest difference with conflate is:
                // when new value emitted but collect is still in process,
                // it will cancel running collection and will collect new emitted value
                .collectLatest {
                    println("LOG_TAG -> Now eating $it")
                    delay(1500)
                    println("LOG_TAG -> Finishing $it")
                }
        }
    }
}