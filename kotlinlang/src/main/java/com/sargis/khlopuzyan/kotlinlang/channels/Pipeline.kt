package com.sargis.khlopuzyan.kotlinlang.channels

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking

private fun main() {
    runBlocking {
        val numbers = produceNumbers()
//        val squares  = square(numbers)
//
//        repeat(5) {
//            println(squares.receive()) // print first five
//        }
//
//        println("Done!") // we are done
//        coroutineContext.cancelChildren() // cancel children coroutines

        val channel = filter(numbers, 4)
        repeat(14) {
            val number = channel.receive()
            println("number: $number")
        }
        coroutineContext.cancelChildren()
    }
}

fun CoroutineScope.produceNumbers() = produce<Int> {
    var x = 1
    while (true) send(x++) // infinite stream of integers starting from 1
}

fun CoroutineScope.square(numbers: ReceiveChannel<Int>): ReceiveChannel<Int> = produce {
    for (x in numbers) send(x * x)
}

fun CoroutineScope.filter(numbers: ReceiveChannel<Int>, prime: Int) = produce<Int> {
    for (x in numbers) if (x % prime != 0) send(x)
}