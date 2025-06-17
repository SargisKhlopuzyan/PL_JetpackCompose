package com.sargis.khlopuzyan.kotlinlang.classAndObjects

private abstract class AbstractClass {
    abstract fun abstractFunction()
    open fun normalFunction1() {

    }
}

private class NormalClass : AbstractClass() {
    override fun abstractFunction() {

    }

    override fun normalFunction1() {

    }
}