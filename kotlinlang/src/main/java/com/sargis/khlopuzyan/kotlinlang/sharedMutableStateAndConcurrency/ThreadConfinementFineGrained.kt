package com.sargis.khlopuzyan.kotlinlang.sharedMutableStateAndConcurrency

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

private val counterContext = newSingleThreadContext("CounterContext")
private var counter = 0

fun main() {
    runBlocking {
        withContext(Dispatchers.Default) {
            massiveRun {
                withContext(counterContext) {
                    counter++
                }
            }
        }

        // Thread confinement coarse-grained
        withContext(counterContext) {
            massiveRun {
                counter++
            }
        }
    }
}