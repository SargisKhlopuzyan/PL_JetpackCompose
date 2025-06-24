package com.sargis.khlopuzyan.kotlinlang.classAndObjects

import kotlinx.coroutines.runBlocking

private fun main() {
    runBlocking {
//        InitOrderDemo("Hello")
        InitOrderDemo("Hello", 36)

        val customClass = CustomClass(36)
        CustomClass.xx
        CustomClass.CUSTOM_COMPANION_OBJECT.yy
    }
}

/**
 * If the primary constructor does not have any annotations or visibility modifiers, the constructor keyword can be omitted
 * */
private open class CustomClass /*private*/ constructor(protected val age: Int) {
    val x = 10
    val y = yy
    companion object CUSTOM_COMPANION_OBJECT {
        val xx = 10
//        val yy = y
        val yy = 10
    }
}

private class InitOrderDemo(name: String) {
    val firstProperty = "First property: $name".also(::println)

    init {
        println("First initializer block that prints $name")
    }

    val secondProperty = "Second property: ${name.length}".also(::println)

    init {
        println("Second initializer block that prints ${name.length}")
    }

    constructor(name: String, age: Int) : this(name) {
        println("Secondary constructor -> name: $name, age: $age")
    }
}