package com.sargis.khlopuzyan.kotlinlang.flows

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

private fun main() {
    runBlocking {
        val time = measureTimeMillis {
            simple()
                .conflate() // conflate emissions, don't process each one
                .collect { value ->
                    delay(250) // pretend we are processing it for 300 ms
                    println(value)
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
