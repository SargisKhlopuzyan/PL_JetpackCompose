package com.sargis.khlopuzyan.kotlinlang.channels

import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//fun main() {
//    runBlocking {
//
//        val channel: Channel<String> = Channel<String>()
//        val sendChannel: SendChannel<String> = channel
//        val receiveChannel: ReceiveChannel<String> = channel
//
//        launch {
//            delay(2000)
//            sendChannel.send("Text_1")
//            sendChannel.send("Text_2")
//            delay(2000)
//        }
//
//        launch {
//            delay(3000)
////            val info = receiveChannel.receiveAsFlow().collect {
//            val info = receiveChannel.receive()
//                println("info: $info")
//            val info2 = receiveChannel.receive()
//            println("info2: $info2")
//            delay(2000)
//        }
//
//    }
//}

fun main() = runBlocking<Unit> {
    val channel = Channel<String>()
    launch {
        delay(1000)
        channel.send("A1")
        channel.send("A2")
        log("A done")
    }
    launch {
        channel.send("B1")
        log("B done")
    }
    launch {
        repeat(4) {
            val x = channel.receive()
            log(x)
        }
        log("END - 3")
    }
}

fun log(message: Any?) {
    println("[${Thread.currentThread().name}] $message")
}

suspend fun funAsync() = coroutineScope {
    val deferred = async {

    }

    val deferred2 = launch {

    }
}