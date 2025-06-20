package com.sargis.khlopuzyan.kotlinlang.classAndObjects

open class Outer {
    private /*open */val a = 1
    protected open val b = 2
    internal open val c = 3
    val d = 4 // public by default

    protected class Nested {
        public val e: Int = 5
    }
}

class Subclass : Outer() {
    // a is not visible
    // b, c and d are visible
    // Nested and e are visible

    override val b = 6   // 'b' is protected
    override val c = 7   // 'c' is internal
//    override val d = 8   // 'd' isn't open
}

class Unrelated(o: Outer) {
    // o.a, o.b are not visible
    // o.c and o.d are visible (same module)
    // Outer.Nested is not visible, and Nested::e is not visible either
}