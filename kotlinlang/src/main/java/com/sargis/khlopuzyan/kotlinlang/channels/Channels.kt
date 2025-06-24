package com.sargis.khlopuzyan.kotlinlang.channels

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.coroutineScope
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

//fun main() = runBlocking<Unit> {
//    val channel = Channel<String>()
//    launch {
//        delay(1000)
//        channel.send("A1")
//        channel.send("A2")
//        log("A done")
//    }
//    launch {
//        channel.send("B1")
//        log("B done")
//    }
//    launch {
//        repeat(4) {
//            val x = channel.receive()
//            log(x)
//        }
//        log("END - 3")
//    }
//}

private fun log(message: Any?) {
    println("[${Thread.currentThread().name}] $message")
}

private suspend fun funAsync() = coroutineScope {
    val deferred = async {

    }

    val deferred2 = launch {

    }
}

//fun main() {
//    runBlocking {
//        val deferred1 = async {
//            delay(2000)
//            1
//        }
//
//        launch {
//            delay(1000)
//            val deferred1Result = deferred1.await()
//            println("deferred1Result: $deferred1Result")
//        }
//    }
//}

private fun main() {
    runBlocking {
//        val channel = Channel<Int>()
//
//        launch {
//            for (x in 1..5) {
//                channel.send(x * x)
//            }
//            channel.close() // we're done sending
//        }
//
//        for (y in channel) {
//            println(y)
//        }

//        repeat(4) {
//            println(channel.receive())
//        }

        val squares = produceSquares()
        squares.consumeEach {
            println(it)
        }

        println("Done!")
    }
}

private fun CoroutineScope.produceSquares(): ReceiveChannel<Int> = produce {
    for (x in 1..5) send(x * x)
}