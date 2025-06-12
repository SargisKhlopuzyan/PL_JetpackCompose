package com.sargis.khlopuzyan.kotlinlang.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * To start a new coroutine, use one of the main coroutine builders:
 *              launch, async, or runBlocking.
 * Different libraries can define additional coroutine builders.
 * */
fun main() {

//    helloWorld()

    runBlocking {
//        launch {
//            delay(2000)
//            println("World!") // print after delay
//        }
//        println("Hello") // main coroutine continues while a previous

//        helloWorld()
//        println("End")

//        explicitlyJob()

        /**
         * deferred & job
         * */
//        val deferred = async {
//            loadData()
//        }
//
//        val job = launch {
//            loadData()
//        }
//
//        deferred.await()
//        deferred.join()
//        job.join()

//        launch {
//            loadContributorsConcurrent()
////            loadContributorsNotCancellable()
//        }.join()

        launch(context = Dispatchers.Default + CoroutineName("CUSTOM_NAME")) {
            val job = coroutineContext[Job]
            val coroutineDispatcher = coroutineContext[CoroutineDispatcher]
            val coroutineName = coroutineContext[CoroutineName]
            val coroutineExceptionHandler = coroutineContext[CoroutineExceptionHandler]
            println("coroutineContext: $coroutineContext")
            println("job: $job")
            println("coroutineDispatcher: $coroutineDispatcher")
            println("coroutineName: $coroutineName")
            println("coroutineExceptionHandler: $coroutineExceptionHandler")
            delay(500)
            launch {
                val job = coroutineContext[Job]
                val coroutineDispatcher = coroutineContext[CoroutineDispatcher]
                val coroutineName = coroutineContext[CoroutineName]
                val coroutineExceptionHandler = coroutineContext[CoroutineExceptionHandler]
                println("coroutineContext: $coroutineContext")
                println("job: $job")
                println("coroutineDispatcher: $coroutineDispatcher")
                println("coroutineName: $coroutineName")
                println("coroutineExceptionHandler: $coroutineExceptionHandler")
            }
        }

        println("End")
    }
}

suspend fun loadContributorsConcurrent(): List<Int> = coroutineScope {
    val deferred = async {
        println("starting loading for *")
        delay(3000)
        println("ending loading for *")
        listOf<Int>(1, 2, 3)
    }

    launch {
        delay(1000)
        this@coroutineScope.cancel()
    }

    deferred.await()
}

suspend fun loadContributorsNotCancellable(): List<Int> = coroutineScope {
    val deferred = GlobalScope.async {
        println("starting loading for **")
        delay(3000)
        listOf<Int>(1, 2, 3)
    }

    launch {
        delay(1000)
        this.cancel()
    }

    deferred.await()

}


suspend fun loadData(): Int {
    println("loading...")
    delay(1000L)
    println("loaded!")
//    throw Exception(" ** EXCEPTION ** ")
    return 42
}

suspend fun helloWorld() = coroutineScope {
    launch {
        delay(100L)
        println("World!")
    }
    println("Hello")
}


suspend fun explicitlyJob() = coroutineScope {
    val job = launch { // launch a new coroutine and keep a reference to its Job
        delay(1000L)
        println("World!")
    }
    println("Hello")
    job.join() // wait until child coroutine completes
    println("Done")
}