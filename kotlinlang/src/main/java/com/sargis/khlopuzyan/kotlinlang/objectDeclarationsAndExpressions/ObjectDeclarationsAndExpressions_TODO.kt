package com.sargis.khlopuzyan.kotlinlang.objectDeclarationsAndExpressions

fun main() {
    val objectClass = ObjectClass
    val dataObjectClass = DataObjectClass

    // ObjectClass@hashcode
    // com.sargis.khlopuzyan.kotlinlang.objectDeclarationsAndExpressions.ObjectClass@27d6c5e0
    println(objectClass)

    // DataObjectClass
    // DataObjectClass
    println(dataObjectClass)
}

/**
 * Objects cant have constructors
 * */
object ObjectClass/*(val x: Int)*/ {
    val number: Int = 10
    fun function1() {
        println("function1")
    }

    fun printClass() {
        println("$this")
    }
}

/**
 * Objects cant have constructors
 *
 * Additionally, the compiler generates several functions for your data object:
 *
 * toString() returns the name of the data object
 *
 * equals()/hashCode() enables equality checks and hash-based collections
 * */
data object DataObjectClass/*(val x: Int)*/ {
    val number: Int = 3
}