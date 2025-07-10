package com.sargis.khlopuzyan.kotlinlang.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.newFixedThreadPoolContext
import kotlinx.coroutines.runBlocking

/**
 * - (1) interface Job : CoroutineContext.Element
 *
 * - (2) launch is a coroutine builder
 * - (3) runBlocking is also a coroutine builder that bridges the non-coroutine world of a regular fun main() and the code with coroutines
 *
 * - (4) A CoroutineScope in Kotlin defines the lifecycle and context for coroutines launched within it.
 *       It acts as a container or parent for coroutines, managing their execution and cancellation.
 *
 * - (5) Coroutines follow a principle of structured concurrency which means that new coroutines can
 *       only be launched in a specific CoroutineScope which delimits the lifetime of the coroutine
 *
 * - (6) *** Scope builder *** In addition to the coroutine scope provided by different builders,
 *   it is possible to declare your own scope using the coroutineScope builder.
 *   It creates a coroutine scope and does not complete until all launched children complete
 *
 * - (7) async is just like launch.
 *       It starts a separate coroutine which is a light-weight thread that works concurrently with all the other coroutines.
 *       The difference is that launch returns a Job and does not carry any resulting value, while async returns a
 *       Deferred — a light-weight non-blocking future that represents a promise to provide a result later.
 *       You can use .await() on a deferred value to get its eventual result, but Deferred is also a Job, so you can cancel it if needed.
 * */
fun main() {
    runBlocking {

        val customCoroutineContext1 =
            newFixedThreadPoolContext(nThreads = 3, name = "CustomCoroutineContext")

        val customCoroutineContext2 = Job() +
                Dispatchers.Main +
                CoroutineExceptionHandler(handler = { _, _ -> }) +
                CoroutineName("CustomName")

        launch(context = customCoroutineContext2, start = CoroutineStart.LAZY, block = {
//            this // this is a CoroutineScope
        })
        launch(customCoroutineContext1) { }
        launch(customCoroutineContext2) { }

        // (6)
        coroutineScope {

        }


        CoroutineScope(coroutineContext).launch {  }
        CoroutineScope(customCoroutineContext1).launch {  }
        CoroutineScope(customCoroutineContext2).launch {  }

        MainScope().launch {

        }


        doAnyWork()

        val job = coroutineContext[Job]
        val coroutineDispatcher = coroutineContext[CoroutineDispatcher]
        val coroutineName = coroutineContext[CoroutineName]
        val coroutineExceptionHandler = coroutineContext[CoroutineExceptionHandler]
    }
}

suspend fun doAnyWork() = coroutineScope {  // this: CoroutineScope
    launch {
        delay(1000L)
        println("World!")
    }
    println("Hello")
    12
}