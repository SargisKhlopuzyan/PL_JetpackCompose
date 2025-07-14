package com.sargis.khlopuzyan.presentation.ui.compose.kotlinHotFlows

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// https://www.youtube.com/watch?v=za-EEkqJLCQ
class KotlinHotFlowsViewModel : ViewModel() {

    private val _stateFlow = MutableStateFlow(0)
    val stateFlow = _stateFlow.asStateFlow()

    private val _sharedFlow = MutableSharedFlow<Int>()
    val sharedFlow = _sharedFlow.asSharedFlow()

    // replay means it will cash last 5 emits
    private val _sharedCashedFlow = MutableSharedFlow<Int>(replay = 5)
    val sharedCashedFlow = _sharedCashedFlow.asSharedFlow()

    fun incrementCounterStateFlow() {
//        _stateFlow.value = stateFlow.value + 1
//        _stateFlow.value = _stateFlow.value + 1
//        _stateFlow.value = stateFlow.value ++ // Error
//        _stateFlow.value = _stateFlow.value ++
        _stateFlow.value += 1
    }

    fun collectSharedFlow() {
//        viewModelScope.launch {
//            sharedFlow.collect {
//                delay(2000)
//                println("LOG_TAG -> FIRST FLOW : The received number is $it")
//            }
//        }
//        viewModelScope.launch {
//            sharedFlow.collect {
//                delay(3000)
//                println("LOG_TAG -> SECOND FLOW : The received number is $it")
//            }
//        }
        squareNumberSharedFlow(3)
    }

    fun squareNumberSharedFlow(number: Int) {
        viewModelScope.launch {
            delay(300)
            _sharedFlow.emit(number * number)
        }
    }
}