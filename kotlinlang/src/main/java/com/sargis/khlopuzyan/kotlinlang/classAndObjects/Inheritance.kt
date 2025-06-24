package com.sargis.khlopuzyan.kotlinlang.classAndObjects

import kotlinx.coroutines.runBlocking


private fun main() {
    runBlocking {
        val base = Base(10)
        base.valProperty

        val interfaceImplClass = InterfaceImplClass(10)
        interfaceImplClass.count
        val base2: Base = Child(a = 10, b = 20)
    }

}

private interface BaseInterface {
    val count: Int
}

private class InterfaceImplClass(override val count: Int) : BaseInterface {

}

private open class Base(open val x: Int) {
    constructor (xx: Int, yy: Int) : this(xx)

    open val valProperty = x * 2
    open var varProperty = x * 2
}

private class Child(override val x: Int = 1111111, val a: Int, val b: Int = 99.also { println("x: $x") }) :
    Base(a, b) {
    override var valProperty: Int = a + b
//    override val varProperty: Int = a + b
}