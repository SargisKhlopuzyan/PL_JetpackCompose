package com.sargis.khlopuzyan.kotlinlang.classAndObjects

private fun main() {
    val functionalInterface = object : FunctionalInterface {
        override fun onClick() {
            println("functionalInterface -> onClick")
        }
    }

    val functionalInterface2 = FunctionalInterface {
        println("functionalInterface2 -> onClick")
    }

    functionalInterface.width
    functionalInterface.getSize()
    functionalInterface.onClick()

    functionalInterface2.onClick()

    PrinterBase {

    }
    Printer {

    }

    SAM_PrinterBase {

    }
}

fun interface FunctionalInterface {

    fun onClick()

    fun getSize(): Int {
        return width * width
    }

    val width: Int
        get() = 10
}

fun PrinterBase(block: () -> Unit): PrinterBase = object : PrinterBase {
    override fun print() = block()
}

fun Printer(block: () -> Unit): PrinterBase = object : PrinterBase {
    override fun print() = block()
}

interface PrinterBase {
    fun print()
    fun doAnyJob() {}
}

fun interface SAM_PrinterBase {
    fun print()
    fun doAnyJob() {}
}