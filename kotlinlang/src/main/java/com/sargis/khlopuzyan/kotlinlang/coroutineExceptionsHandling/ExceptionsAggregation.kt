package com.sargis.khlopuzyan.kotlinlang.coroutineExceptionsHandling

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.IOException
import kotlin.coroutines.cancellation.CancellationException

//fun main(): Unit = runBlocking {
//    val handler = CoroutineExceptionHandler { _, exception ->
//        println("CoroutineExceptionHandler got $exception with suppressed ${exception.suppressed.contentToString()}")
//    }
//
//    val job = GlobalScope.launch(handler) {
//        launch {
//            try {
//                delay(Long.MAX_VALUE) // it gets cancelled when another sibling fails with IOException
//            } finally {
//                throw ArithmeticException() // the second exception
//            }
//        }
//        launch {
//            delay(100)
//            throw IOException() // the first exception
//        }
//        delay(Long.MAX_VALUE)
//    }
//
//    job.join()
//}

@OptIn(DelicateCoroutinesApi::class)
private fun main() = runBlocking {
    val handler = CoroutineExceptionHandler { _, exception ->
        println("CoroutineExceptionHandler got $exception")
    }
    val job = GlobalScope.launch(handler) {

        val innerJob = launch { // all this stack of coroutines will get cancelled
            launch {
                launch {
                    throw IOException() // the original exception
                }
            }
        }

        try {
            innerJob.join()
        } catch (e: CancellationException) {
            println("Rethrowing CancellationException with original cause")
            throw e // cancellation exception is rethrown, yet the original IOException gets to the handler
        }
    }
    job.join()
}