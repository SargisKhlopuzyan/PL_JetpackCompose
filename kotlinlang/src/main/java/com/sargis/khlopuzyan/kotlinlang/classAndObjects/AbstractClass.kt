package com.sargis.khlopuzyan.kotlinlang.classAndObjects

abstract class AbstractClass {
    abstract fun abstractFunction()
    open fun normalFunction1() {

    }
}

class NormalClass : AbstractClass() {
    override fun abstractFunction() {

    }

    override fun normalFunction1() {

    }
}