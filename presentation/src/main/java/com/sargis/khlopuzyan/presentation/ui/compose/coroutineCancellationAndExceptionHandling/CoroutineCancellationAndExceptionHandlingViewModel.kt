package com.sargis.khlopuzyan.presentation.ui.compose.coroutineCancellationAndExceptionHandling

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize

@Parcelize
data class UiState(val catchableText: String = "", val printText: String = "") : Parcelable

class CoroutineCancellationAndExceptionHandlingViewModel(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val uiState = savedStateHandle.getStateFlow("uiState", UiState())

    val handler = CoroutineExceptionHandler { _, throwable ->
        println("LOG_TAG -> Caught exception: $throwable")
        savedStateHandle["uiState"] = UiState(
            catchableText = "Caught exception: $throwable",
            printText = "Caught exception: $throwable"
        )
    }

    // CRASH
    fun launchACoroutinesInLaunchBlock() {
        savedStateHandle["uiState"] = UiState(
            catchableText = "",
            printText = ""
        )

        viewModelScope.launch {
            try {
                launch {
                    launch {
                        throw Exception("LOG_TAG -> error - launchACoroutinesInLaunchBlock")
                    }
                }
            } catch (e: Exception) {
                savedStateHandle["uiState"] = UiState(
                    catchableText = "Caught exception: $e",
                    printText = "Caught exception: $e"
                )

                println("LOG_TAG -> Caught exception: $e")
            }
        }
    }

    fun launchACoroutineInLaunchBlockWithParentHandler() {
        savedStateHandle["uiState"] = UiState(
            catchableText = "",
            printText = ""
        )
        viewModelScope.launch(handler) {
            /*val string =*/
            launch/*(handler)*/ {
                delay(200)
                throw Exception("LOG_TAG -> error - launchACoroutineInLaunchBlockWithHandler")
                "LOG_TAG -> Result"
            }
        }
    }

    // CRASH ?
    fun launchACoroutineInLaunchBlockWithChildHandler() {
        savedStateHandle["uiState"] = UiState(
            catchableText = "",
            printText = ""
        )
        viewModelScope.launch/*(handler)*/ {
            /*val string =*/
            launch(handler) {
                delay(200)
                throw Exception("LOG_TAG -> error - launchACoroutineInLaunchBlockWithHandler")
                "LOG_TAG -> Result"
            }
        }
    }

    // CRASH
    fun launchAnAsyncInLaunchBlock() {
        savedStateHandle["uiState"] = UiState(
            catchableText = "",
            printText = ""
        )
        viewModelScope.launch {
            /*val string =*/
            async {
                delay(200)
                throw Exception("LOG_TAG -> error - launchAnAsyncInLaunchBlock")
                "launchAnAsyncInLaunchBlock -> Result"
            }
//            println(string.await())
        }
    }

    // CRASH
    fun launchAnAsyncInLaunchBlockWithAwait() {
        savedStateHandle["uiState"] = UiState(
            catchableText = "",
            printText = ""
        )
        viewModelScope.launch {
            val string = async {
                delay(200)
                throw Exception("LOG_TAG -> error - launchAnAsyncInLaunchBlockWithAwait")
                "launchAnAsyncInLaunchBlockWithAwait -> Result"
            }
            val str = string.await()
            savedStateHandle["uiState"] = UiState(
                catchableText = "",
                printText = str
            )
            println("LOG_TAG -> $str")
        }
    }

    fun launchAnAsyncInLaunchBlockWithParentHandler() {
        savedStateHandle["uiState"] = UiState(
            catchableText = "",
            printText = ""
        )
        viewModelScope.launch(handler) {
            /*val string = */
            async/*(handler)*/ {
                delay(200)
                throw Exception("LOG_TAG -> error - launchAnAsyncInLaunchBlockWithHandler")
                "LOG_TAG -> Result"
            }
//            println(string.await())
        }
    }

    // CRASH ?
    fun launchAnAsyncInLaunchBlockWithChildHandler() {
        savedStateHandle["uiState"] = UiState(
            catchableText = "",
            printText = ""
        )
        viewModelScope.launch/*(handler)*/ {
            /*val string = */
            async(handler) {
                delay(200)
                throw Exception("LOG_TAG -> error - launchAnAsyncInLaunchBlockWithHandler")
                "LOG_TAG -> Result"
            }
//            println(string.await())
        }
    }

    fun launchAnAsyncInLaunchBlockWithParentHandlerAndAwait() {
        savedStateHandle["uiState"] = UiState(
            catchableText = "",
            printText = ""
        )
        viewModelScope.launch(handler) {
            val string = async/*(handler)*/ {
                delay(200)
                throw Exception("LOG_TAG -> error - launchAnAsyncInLaunchBlockWithHandler")
                "LOG_TAG -> Result"
            }

            val str = string.await()
            savedStateHandle["uiState"] = UiState(
                catchableText = "",
                printText = str
            )
            println("LOG_TAG -> $str")
        }
    }

    // CRASH ?
    fun launchAnAsyncInLaunchBlockWithChildHandlerAndAwait() {
        savedStateHandle["uiState"] = UiState(
            catchableText = "",
            printText = ""
        )
        viewModelScope.launch/*(handler)*/ {
            val string = async(handler) {
                delay(200)
                throw Exception("LOG_TAG -> error - launchAnAsyncInLaunchBlockWithHandler")
                "LOG_TAG -> Result"
            }

            val str = string.await()
            savedStateHandle["uiState"] = UiState(
                catchableText = "",
                printText = str
            )
            println("LOG_TAG -> $str")
        }
    }



    fun launchAnAsyncInAsyncBlock() {
        savedStateHandle["uiState"] = UiState(
            catchableText = "",
            printText = ""
        )
        viewModelScope.async {
            /*val string =*/
            async {
                delay(200)
                throw Exception("LOG_TAG -> error - launchAnAsyncInAsyncBlock")
                "launchAnAsyncInAsyncBlock -> Result"
            }
//            println(string.await())
        }
    }

    fun launchAnAsyncInAsyncBlockWithAwait() {
        savedStateHandle["uiState"] = UiState(
            catchableText = "",
            printText = ""
        )
        viewModelScope.async {
            val string = async {
                delay(200)
                throw Exception("LOG_TAG -> error - launchAnAsyncInAsyncBlockWithAwait")
                "LOG_TAG -> Result"
            }
            val str = string.await()
            savedStateHandle["uiState"] = UiState(
                catchableText = "",
                printText = str
            )
            println("LOG_TAG -> $str")
        }
    }

    fun launchAnAsyncInAsyncBlockWithParentHandler() {
        savedStateHandle["uiState"] = UiState(
            catchableText = "",
            printText = ""
        )
        viewModelScope.async(handler) {
            /*val string =*/
            async/*(handler)*/ {
                delay(200)
                throw Exception("LOG_TAG -> error - launchAnAsyncInAsyncBlockWithHandler")
                "LOG_TAG -> Result"
            }
//            println(string.await())
        }
    }

    fun launchAnAsyncInAsyncBlockWithChildHandler() {
        savedStateHandle["uiState"] = UiState(
            catchableText = "",
            printText = ""
        )
        viewModelScope.async/*(handler)*/ {
            /*val string =*/
            async(handler) {
                delay(200)
                throw Exception("LOG_TAG -> error - launchAnAsyncInAsyncBlockWithHandler")
                "LOG_TAG -> Result"
            }
//            println(string.await())
        }
    }

    fun launchAnAsyncInAsyncBlockWithParentHandlerAndAwait() {
        savedStateHandle["uiState"] = UiState(
            catchableText = "",
            printText = ""
        )
        viewModelScope.async(handler) {
            val string = async/*(handler)*/ {
                delay(200)
                throw Exception("LOG_TAG -> error - launchAnAsyncInAsyncBlockWithHandler")
                "LOG_TAG -> Result"
            }
            val str = string.await()
            savedStateHandle["uiState"] = UiState(
                catchableText = "",
                printText = str
            )
            println("LOG_TAG -> $str")
        }
    }

    fun launchAnAsyncInAsyncBlockWithChildHandlerAndAwait() {
        savedStateHandle["uiState"] = UiState(
            catchableText = "",
            printText = ""
        )
        viewModelScope.async/*(handler)*/ {
            val string = async(handler) {
                delay(200)
                throw Exception("LOG_TAG -> error - launchAnAsyncInAsyncBlockWithHandler")
                "LOG_TAG -> Result"
            }
            val str = string.await()
            savedStateHandle["uiState"] = UiState(
                catchableText = "",
                printText = str
            )
            println("LOG_TAG -> $str")
        }
    }
}