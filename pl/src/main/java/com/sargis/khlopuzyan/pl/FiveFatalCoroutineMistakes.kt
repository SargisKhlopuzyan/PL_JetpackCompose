package com.sargis.khlopuzyan.pl

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlin.random.Random

// https://www.youtube.com/watch?v=cr5xLjPC4-0&ab_channel=PhilippLackner
fun main() = runBlocking {
    /*** Mistake 1 ******************************************/
    val usersFirstNames = getUserFirstNames((0..100).toList().map { it.toString() })
    println("LOG_TAG -> usersFirstNames: $usersFirstNames")

    /*** Mistake 2 ******************************************/
    doSomething()

    /*** Mistake 3 ******************************************/
    doNetworkCall()

    /*** Mistake 4 ******************************************/
    riskyTask()
}

/*** Mistake 1 ******************************************/
private suspend fun getUserFirstNames(userIds: List<String>): List<String> {
    val firstNames = mutableListOf<Deferred<String>>()
    coroutineScope {
        userIds.forEach { userId ->
            val userDeferred = async {
                getFirstName(userId)
            }
            firstNames.add(userDeferred)
        }
    }
    return firstNames.awaitAll()
}

private suspend fun getFirstName(userId: String): String {
    delay(1000)
    return "First name $userId"
}

/*** Mistake 2 ******************************************/
private suspend fun doSomething() {
    val job = CoroutineScope(Dispatchers.Default).launch {
        var random = Random.nextInt(100_000)
//        while (random != 50000) {
        // TODO - 1st solution
        while (random != 50000 && isActive) {
            random = Random.nextInt(100_000)
            // TODO - 2nd solution
            ensureActive()
        }
    }
    delay(500)
    job.cancel()
}

/*** Mistake 3 ******************************************/
private suspend fun doNetworkCall(): Result<String> {
    val result = networkCall()
    return if (result == "Success") {
        Result.success(result)
    } else {
        Result.failure(Exception())
    }
}

private suspend fun networkCall(): String {
    // TODO - The network call suspend function is not main safe
//    delay(3000)
//    return if (Random.nextBoolean()) "Success" else "Error"
    return withContext(Dispatchers.IO) {
        delay(3000)
        if (Random.nextBoolean()) "Success" else "Error"
    }
}

/*** Mistake 4 ******************************************/
private suspend fun riskyTask() {
    try {
        delay(3000)
        println("The answer is ${10 / 0}")
    } catch (e: Exception) {
        // TODO - in case we have try/catch we must manually throw exception in catch block
        if (e is ArithmeticException) {
            throw e
        }
        println("Oops, that didn't work")
    }
}

/*** Mistake 5 ******************************************/
// TODO - don't use lifecycleScope.launch { viewModel.doNetworkCall() } to make api calls, just use viewModelScope.launch {...}