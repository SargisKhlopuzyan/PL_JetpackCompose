package com.sargis.khlopuzyan.pl

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


//https://www.youtube.com/watch?v=Itb5fu4UVt4&ab_channel=PhilippLackner
private fun main() {

    val list = (1..100).toList()
    list.normalForEach {
        // return TODO-Error
        return@normalForEach //TODO-OK
        println(it)
    }

//    list.forEach {
//        return //TODO-OK
//        return@forEach //TODO-OK
//    }

    list.inlinedForEach {
        return //TODO-OK
        return@inlinedForEach //TODO-OK
        println(it)
    }

    list.crossinlinedForEach {
//        return //TODO-ERROR
        return@crossinlinedForEach //TODO-OK
        println(it)
    }

    /*****************************************/
    CoroutineScope(Dispatchers.Default).launch {
//        return //TODO-ERROR
        return@launch //TODO-OK
    }
    val async1 = executeAsync {
//        return //TODO-ERROR
        1111
    }
    val async2 = executeAsync {

    }
    /*****************************************/

    runBlocking {
        launch {
            list.normalForEach {
                // normal function doesn't have coroutine scope so t can't suspend
                // delay(1000)
                println(it)
            }
        }

        launch {
            list.inlinedForEach {
                delay(1000)
                println(it)
            }
        }
    }

    /*****************************************/

    "Hello".printClassName()

    /*****************************************/
    //Inline variable

    val lastItem = list.lastItem
    val listItemInlined = list.get(list.lastIndex)
    println(lastItem)

    /*****************************************/
    //Inline class
}

inline fun <reified T> T.printClassName() {
    println(T::class.simpleName)
}

inline fun square(x: Int) = x * x

fun <T> List<T>.normalForEach(action: (T) -> Unit) {
    for (item in this) {
        action(item)
    }
}

inline fun <T> List<T>.inlinedForEach(action: (T) -> Unit) {
    for (item in this) {
        action(item)
    }
}

inline fun <T> List<T>.crossinlinedForEach(crossinline action: (T) -> Unit) {
    for (item in this) {
        action(item)
    }
}

// in case this function is inlined but action is not crossinlined,
// it would be possible to make a return, but as from coroutine scope only
// allowed to return from block not from upper function, the compiler will show an error
inline fun executeAsync(crossinline action: () -> Unit) {
    CoroutineScope(Dispatchers.Default).launch {
        action()
    }
}

/*****************************************/
//Inline variable

// We can use inlined version of this variable
// the compiler will just copy "get() = get(lastIndex)" function in call side
val <T> List<T>.lastItem: T
    get() = get(lastIndex)

/*****************************************/
//Inline class

// Compiler shows an error as value class should be annotated with @JvmInline
@JvmInline
value class Month(val number: Int)
