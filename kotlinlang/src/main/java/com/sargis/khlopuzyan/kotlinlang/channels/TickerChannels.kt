package com.sargis.khlopuzyan.kotlinlang.channels

import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

private fun main() {
    runBlocking {
        val xxxx = produce<Int> {

        }
        val tickerChannel = ticker(delayMillis = 200, initialDelayMillis = 0) // create a ticker channel

        var nextElement = withTimeoutOrNull(1) {
            tickerChannel.receive()
        }
        println("Initial element is available immediately: $nextElement") // no initial delay

        nextElement = withTimeoutOrNull(100) {
            tickerChannel.receive()
        } // all subsequent elements have 200ms delay
        println("Next element is not ready in 100 ms: $nextElement")

        nextElement = withTimeoutOrNull(120) {
            tickerChannel.receive()
        }
        println("Next element is ready in 200 ms: $nextElement")

        // Emulate large consumption delays
        println("Consumer pauses for 300ms")
        delay(300)

        // Next element is available immediately
        nextElement = withTimeoutOrNull(1) {
            tickerChannel.receive()
        }
        println("Next element is available immediately after large consumer delay: $nextElement")

        // Note that the pause between `receive` calls is taken into account and next element arrives faster
        nextElement = withTimeoutOrNull(120) { tickerChannel.receive() }
        println("Next element is ready in 100ms after consumer pause in 300ms: $nextElement")

        tickerChannel.cancel() // indicate that no more elements are needed
    }
}