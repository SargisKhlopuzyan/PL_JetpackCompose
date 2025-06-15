package com.sargis.khlopuzyan.kotlinlang.flows

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() {
    runBlocking {
        val time = measureTimeMillis {
            simple()
                .buffer() // buffer emissions, don't wait
                .collect { value ->
                delay(300) // pretend we are processing it for 300 ms
                log("Collecting $value")
            }
        }
        println("Collected in $time ms")
    }
}

private fun simple(): Flow<Int> = flow {
    for (i in 1..3) {
        delay(200) // pretend we are asynchronously waiting 100 ms
        log("Emitting $i")
        emit(i) // emit next value
    }
}

private fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")
