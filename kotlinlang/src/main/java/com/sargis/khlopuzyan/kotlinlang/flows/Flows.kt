package com.sargis.khlopuzyan.kotlinlang.flows

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun numbers(): Flow<Int> = flow {
    try {
        emit(1)
        emit(2)
        println("This line will not execute")
        emit(3)
    } finally {
        println("Finally in numbers")
    }
}

fun main() {
    runBlocking {
        val numbers = numbers()
        launch {
            numbers.take(2).collect { number ->
                println("* 1 * $number")
            }
        }

        launch {
            numbers.collect { number ->
                println("* 2 * $number")
            }
        }
    }
}