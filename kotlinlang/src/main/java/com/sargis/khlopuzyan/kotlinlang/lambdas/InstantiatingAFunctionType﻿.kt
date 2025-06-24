package com.sargis.khlopuzyan.kotlinlang.lambdas

import kotlin.math.max
import kotlin.reflect.KFunction

private fun main() {
    val customIntClass = CustomIntClass()
//    val lambda1: KFunction<CustomIntClass> = ::CustomIntClass
//    val lambda: (String) -> Int? = ::CustomIntClass
//    val lambda: (String) -> Int? = CustomIntClass()
    val lambda: (String, String) -> Int? = CustomIntClass()

    /************************************************************************************/
    maxFunction(listOf("a","bb","ccc"), { a: String, b: String -> a.length < b.length })

    val anonymousFunction = fun(a: String, b: String): Boolean = a.length < b.length
    maxFunction(listOf("a","bb","ccc"), anonymousFunction)
}

fun maxFunction(items: List<String>, condition: (String, String) -> Boolean) {

}


fun compare(a: String, b: String): Boolean = a.length < b.length


class CustomIntClass : /*(String) -> Int?,*/ (String, String) -> Int {
//    override fun invoke(p1: String): Int? {
//        return p1.toIntOrNull()
//    }

    override fun invoke(p1: String, p2: String): Int {
        TODO("Not yet implemented")
    }
}