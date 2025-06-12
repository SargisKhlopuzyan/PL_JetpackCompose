package com.sargis.khlopuzyan.kotlinlang.coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() {
    val time = measureTimeMillis {
        val one = function1Async()
        val two = function2Async()

        runBlocking {
            println("The answer is ${one.await() + two.await()}")
        }
    }
}

fun function1Async() = GlobalScope.async {
    function1()
}

fun function2Async() = GlobalScope.async {
    function2()
}

suspend fun function1(): Int {
    delay(1000L)
    return 13
}

suspend fun function2(): Int {
    delay(1000L)
    return 29
}