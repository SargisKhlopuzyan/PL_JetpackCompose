package com.sargis.khlopuzyan.kotlinlang.flows

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.system.*

private fun main() {
    runBlocking {
        val time = measureTimeMillis {
            simple().collectLatest { value ->
                println("Collecting $value")
                delay(300) // pretend we are processing it for 300 ms
                println("Done $value")
            }
        }
        println("Collected in $time ms")
    }
}

private fun simple(): Flow<Int> = flow {
    for (i in 1..5) {
        Thread.sleep(100)
        log("Emitting $i")
        emit(i)
    }
}.flowOn(Dispatchers.Default)

private fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")
