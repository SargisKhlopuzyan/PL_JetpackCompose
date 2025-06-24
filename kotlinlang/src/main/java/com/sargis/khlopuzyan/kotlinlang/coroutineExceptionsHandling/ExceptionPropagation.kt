package com.sargis.khlopuzyan.kotlinlang.coroutineExceptionsHandling

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//fun main() {
//    runBlocking {
//
//        val job = GlobalScope.launch { // root coroutine with launch
//            println("Throwing exception from launch")
//            throw IndexOutOfBoundsException() // Will be printed to the console by Thread.defaultUncaughtExceptionHandler
//        }
//        job.join()
//        println("Joined failed job")
//
//        val deferred = GlobalScope.async { // root coroutine with async
//            println("Throwing exception from async")
//            throw ArithmeticException() // Nothing is printed, relying on user to call await
//        }
//
//        try {
//            deferred.await()
//            println("Unreached")
//        } catch (e: ArithmeticException) {
//            println("Caught ArithmeticException")
//        }
//    }
//}

private fun main() {
    runBlocking {

        val handler = CoroutineExceptionHandler { _, exception ->
            println("CoroutineExceptionHandler got $exception")
        }

        val job = GlobalScope.launch(handler) { // root coroutine with launch
            println("Throwing exception from launch")
            throw IndexOutOfBoundsException() // Will be printed to the console by Thread.defaultUncaughtExceptionHandler
        }
        job.join()
        println("Joined failed job")

        val deferred = GlobalScope.async(handler) { // root coroutine with async
            println("Throwing exception from async")
            throw ArithmeticException() // Nothing is printed, relying on user to call await
        }

        joinAll(job, deferred)

//        try {
//            deferred.await()
            println("Unreached")
//        } catch (e: ArithmeticException) {
//            println("Caught ArithmeticException")
//        }
    }
}