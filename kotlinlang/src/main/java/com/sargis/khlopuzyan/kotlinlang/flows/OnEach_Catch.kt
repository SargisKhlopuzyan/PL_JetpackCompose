package com.sargis.khlopuzyan.kotlinlang.flows

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
//        simple()
//            .catch { e -> emit("Caught : $e") } // emit on exception
//            .collect { value -> println(value) }

//        simple().onEach { value ->
//            check(value <= 1) { "Collected $value" }
//            println(value)
//        }.catch { e ->
//            println("Caught $e")
//        }.collect()

//        events()
//            .onEach { event ->
//                println("Event: $event")
//            }
//            .collect()
//        println("Done * 1\n")

        val scope = GlobalScope
        val job = scope.launch {
            delay(3000)
            println("GlobalScope *** 1")
        }

        events()
            .onEach { event -> println("Event: $event") }
            .launchIn(scope) // <--- Launching the flow in a separate coroutine

        job.join()

        println("Done * 2")
    }
}

private fun events(): Flow<Int> = (1..3).asFlow().onEach {
    delay(100)
}

private fun simple(): Flow<Int> = flow {
    for (i in 1..3) {
        println("Emitting $i")
        emit(i)
    }
}.map { value ->
    check(value <= 1) {
        "Exception -> Crashed on $value"
    }
    value
}