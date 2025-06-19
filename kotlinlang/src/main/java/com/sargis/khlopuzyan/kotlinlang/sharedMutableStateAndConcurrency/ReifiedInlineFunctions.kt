package com.sargis.khlopuzyan.kotlinlang.sharedMutableStateAndConcurrency

import com.google.gson.Gson
import kotlin.math.abs
import kotlin.math.cos

fun main() {
    val jsonString = """{"id":1,"name":"Bob","email":"bob@example.com"}"""
    jsonString.toKotlinObject(MyJsonType::class.java)
    jsonString.toKotlinObject_ReifiedAndInline<MyJsonType>()

    val result = 5 multiple 78
    val resultNull = null multiple 78
    println("result: $result")
    println("resultNull: $resultNull")
}

data class MyJsonType(val name: String)

fun <T> String.toKotlinObject(c: Class<T>): T {
    val gson = Gson()
//    val user = gson.fromJson(this, T::class.java)
    val user = gson.fromJson(this, c)
    return user
}

inline fun <reified T> String.toKotlinObject_ReifiedAndInline(): T {
    val gson = Gson()
    val user = gson.fromJson(this, T::class.java)
    return user
}

infix fun Int?.multiple(other: Int): Int {
    return (this ?: 0) * other
}

fun localFunction(graph: String) {
    val visited = HashSet<String>()

    fun localFunction1(current: String) {
        if (!visited.add(current)) return
        for (v in current) {
            localFunction("$v")
            localFunction1("$v")
//            localFunction2("$v")
//            localFunction3("$v")
        }
    }

    fun localFunction2(current: String) {
        if (!visited.add(current)) return
        for (v in current) {
            localFunction("$v")
            localFunction1("$v")
            localFunction2("$v")
//            localFunction3("$v")
        }
    }
    localFunction2("${graph[0]}")

    fun localFunction3(current: String) {
        if (!visited.add(current)) return
        for (v in current) {
            localFunction("$v")
            localFunction2("$v")
        }
    }
    localFunction2("${graph[0]}")
}


// Tail recursive functions
val eps = 1E-10 // "good enough", could be 10^-15

tailrec fun findFixPoint(x: Double = 1.0): Double =
    if (abs(x - cos(x)) < eps) x else findFixPoint(cos(x))