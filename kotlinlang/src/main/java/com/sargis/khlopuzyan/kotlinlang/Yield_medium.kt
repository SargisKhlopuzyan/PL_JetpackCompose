package com.sargis.khlopuzyan.kotlinlang

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield

fun main() {
    runBlocking {
        val jobA = launch {
            repeat(5) {
                println("Coroutine A - iteration $it")
                yield() // Yield control to allow other coroutines to run
            }
        }

        val jobB = launch {
            repeat(5) {
                println("Coroutine B - iteration $it")
                yield() // Yield control to allow other coroutines to run
            }
        }

        jobA.join()
        jobB.join()
    }
}