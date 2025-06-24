package com.sargis.khlopuzyan.kotlinlang.flows

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//fun main() {
//    runBlocking {
//        val sum = (1..5).asFlow().map {
//            it * it
//        }.reduce { a, b ->
//            // 1, 4, 9, 16, 25
//            println("a: $a, b: $b")
//            a + b
//        }
//        println("sum: $sum")
//
//
//    }
//}

private fun main() {
    runBlocking(CoroutineName("RUN_BLOCKING")) {
        println("runBlocking->scope: $this")
        println("runBlocking->coroutineContext: ${this.coroutineContext}")
        println("runBlocking->coroutineContext[CoroutineName]: ${this.coroutineContext[CoroutineName]}")
        println("runBlocking->coroutineContext[Job]: ${this.coroutineContext[Job]}")
        println("runBlocking->coroutineContext[CoroutineDispatcher]: ${this.coroutineContext[CoroutineDispatcher]}")
//        val coroutineContext = CoroutineName("Name") + Job() + Dispatchers.Default
//        val scope = CoroutineScope(coroutineContext)
//        scope.launch {
        println("\n********\n")
        launch(CoroutineName("LAUNCH")) {
//            withContext(coroutineContext) {
            println("launch->scope: $this")
            println("launch->coroutineContext: ${this.coroutineContext}")
            println("launch->coroutineContext[CoroutineName]: ${this.coroutineContext[CoroutineName]}")
            println("launch->coroutineContext[Job]: ${this.coroutineContext[Job]}")
            println("launch->coroutineContext[CoroutineDispatcher]: ${this.coroutineContext[CoroutineDispatcher]}")
//            }
        }.join()

    }
}