package com.sargis.khlopuzyan.kotlinlang.enumClasses


enum class ProtocolState {
    WAITING {
        override fun signal() = TALKING
        fun otherFunction() = 10
    },

    TALKING {
        override fun signal() = WAITING
    };

    abstract fun signal(): ProtocolState
}

//

interface Interface1 {
    fun function1(): Unit
}

interface Interface2 {
    fun function2(): Unit
}

enum class EnumClass : Interface1, Interface2 {
    FIRST {
        override fun function1() {
            println("FIRST -> function1")
        }

//        override fun function2() {
//            println("FIRST -> function2")
//        }
    },
    SECOND {
        override fun function1() {
            println("SECOND -> function1")
        }

//        override fun function2() {
//            println("SECOND -> function2")
//        }
    };

    override fun function2() {
        println("function2")
    }
}