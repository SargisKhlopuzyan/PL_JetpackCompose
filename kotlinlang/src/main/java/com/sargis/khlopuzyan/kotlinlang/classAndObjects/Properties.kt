package com.sargis.khlopuzyan.kotlinlang.classAndObjects

private fun main() {
    val base2 = Base2()
}

private class Base2 {


    val simple: Int? // has type Int, default getter, must be initialized in constructor
    val inferredType = 1 // has type Int and a default getter

    var setterVisibility: String = "abc"
        private set // the setter is private and has th\
    // e default implementation

//    var setterWithAnnotation: Any? = null
//        @Inject set // annotate the setter with Inject

    constructor() {
        println("constructor * 1 simple: $simple")
//        simple = 10
    }

    init {
//        println("init * 1 simple: $simple")
        simple = 15
    }

    init {
        println("init * 2")
//        simple = 15
    }

//    constructor() {
//        println("constructor * 2")
////        simple = 10
//    }
}