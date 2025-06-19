package com.sargis.khlopuzyan.kotlinlang.lambdas

fun main() {
    val a = A()
    val b = B()
    val c: C = a.extendedFunction(b)

    val callbackReference: (String) -> Int = ::normalFunction
    callbackReference("AnyText")
}

class A
class B
class C

private val myFunction: (Int) -> String = { x ->
    "$x"
}

private val onClick: () -> Unit = {

}

//val extendedFunction: A.((B) -> C) = { b -> // ERROR
private val extendedFunction: A.(B) -> C = { b ->
    C()
}

/**
 * Instantiating a function type
 *
 * */
private val lambdaFunction = { a: Int, b: Int ->
    a + b
}

private val anonymousFunction = fun(s: String): Int {
    return s.toIntOrNull() ?: 0
}

private fun normalFunction(s: String): Int {
    return s.toInt()
}
