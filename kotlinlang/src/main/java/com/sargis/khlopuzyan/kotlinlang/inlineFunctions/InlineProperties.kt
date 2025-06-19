package com.sargis.khlopuzyan.kotlinlang.inlineFunctions

val foo: Foo
    inline get() = Foo()

//var bar: Bar
//    inline set(v) {
//        field = v
//    }

class Foo
class Bar