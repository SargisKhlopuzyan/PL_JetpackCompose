package com.sargis.khlopuzyan.kotlinlang.coroutines

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")

@OptIn(DelicateCoroutinesApi::class)
fun main() {
    newSingleThreadContext("Ctx1").use { ctx1 ->
        newSingleThreadContext("Ctx2").use { ctx2 ->
            runBlocking(ctx1) {
                log("Started in ctx1")
                withContext(ctx2) {
                    delay(1000)
                    log("Working in ctx2")
                }
                log("Back to ctx1")
            }
        }
    }
}