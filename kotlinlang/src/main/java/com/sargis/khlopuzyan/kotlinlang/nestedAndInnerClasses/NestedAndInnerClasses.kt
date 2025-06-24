package com.sargis.khlopuzyan.kotlinlang.nestedAndInnerClasses

private fun main() {
    val nested = Outer.Nested()
    val inner = Outer().Inner()

    val innerClass = OuterInterface.InnerClass()
    // Anonymous inner classes
    val innerInterface = object : OuterInterface.InnerInterface {}
}

class Outer {
    private val bar: Int = 1

    class Nested {
        fun foo() = 2
    }

    inner class Inner {
        fun boo() = 4
    }
}

interface OuterInterface {
    class InnerClass
    interface InnerInterface
}