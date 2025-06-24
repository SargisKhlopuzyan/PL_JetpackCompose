package com.sargis.khlopuzyan.kotlinlang.generics

// declaration-site variance

private fun main() {
    sort(listOf("S"))

    val result = printTypeName<String>()
    val result1 = functionInlineReifiedGeneric<String>("String")
    /*val result2 = */functionGeneric<String>("String")
}

class ClassInt<in T>(/*var tValue: T*/) {

    private val items = mutableListOf<T>()

    fun inputFunction(t: T) {
        items.add(t)
    }

//    fun returnFunction(): MutableList<T> {
//        return items
//    }
}

class ClassOut<out T>(val t: T) {
    //    var tValue: T = t // with "out"
    fun returnFunction(): T = t
}

fun <T : Comparable<T>> sort(list: List<T>) {

}

// Reified

inline fun <reified T> printTypeName() {
    println("Type of T: ${T::class.simpleName}")
}

fun <T> printTypeName2() {
//    println("Type of T: ${T::class.simpleName}")
}

fun <T> functionGeneric(item: T): T {
    return item
}

inline fun <reified T> functionInlineReifiedGeneric(item: T): T {
    return item
}