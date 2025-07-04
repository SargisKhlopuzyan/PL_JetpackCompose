package com.sargis.khlopuzyan.kotlinlang.coroutineExceptionsHandling

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield

private fun main() {
    runBlocking {
        val job = launch {
            val child = launch {
                try {
                    delay(Long.MAX_VALUE)
                } finally {
                    println("Child is cancelled")
                }
            }
            yield()
            println("Cancelling child")
            child.cancel()
            child.join()
            yield()
            println("Parent is not cancelled")
        }

        println("job.join()")
        job.join()

        println("runBlocking - Finished")
    }
    println("main - Finished")
}