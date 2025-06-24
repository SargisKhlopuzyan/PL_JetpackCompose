package com.sargis.khlopuzyan.kotlinlang.flows

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

/**
 * Another thing to observe here is that the flowOn operator has changed the default sequential nature of the flow.
 * Now collection happens in one coroutine ("coroutine#1") and emission happens in another coroutine ("coroutine#2")
 * that is running in another thread concurrently with the collecting coroutine.
 * The flowOn operator creates another coroutine for an upstream flow when it has to change the CoroutineDispatcher in its context.
 * */
private fun main() {
    runBlocking {
//        // Exception in thread "main" java.lang.IllegalStateException: Flow invariant is violated:
//        simple_returns_IllegalStateException().collect {
//                value -> println(value)
//        }

//        withContext((Dispatchers.IO)) {
        simple().collect { value ->
            log("Collected: $value")
        }
//        }
    }
}

private fun simple_returns_IllegalStateException(): Flow<Int> = flow {
    // The WRONG way to change context for CPU-consuming code in flow builder
    withContext(Dispatchers.Default) {
        for (i in 1..3) {
            Thread.sleep(100) // pretend we are computing it in CPU-consuming way
            emit(i) // emit next value
        }
    }
}

private fun simple(): Flow<Int> = flow {
    for (i in 1..3) {
        Thread.sleep(100)
        log("Emitting $i")
        emit(i)
    }
}.flowOn(Dispatchers.Default)

private fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")