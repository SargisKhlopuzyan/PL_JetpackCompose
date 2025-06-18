package com.sargis.khlopuzyan.kotlinlang

// "::"

fun main() {
    functionReference2()
    Example().printFunctionType()
//    Example.printFunctionType() // Compiler error
    Example.printCompanion()

//    Host("Name").printConnectionString()
    val connection = Connection(Host("Name"), 111)
//    connection.host.printConnectionString()
    connection.connect()
}

fun sayHello() {
    
}

val functionReference: () -> Unit = {}
val functionReference2: () -> Unit = ::sayHello


class Example {
    fun printFunctionType() { println("Class method") }

    companion object {

    }
}

fun Example.printFunctionType() { println("Extension function") }

//val Example.number = 1 // error: initializers are not allowed for extension properties
val Example.number
    get() = 1

fun Example.Companion.printCompanion() {
    println("Companion")
}

/**
 * Declaring extensions as members
 * */

class Host(val hostname: String) {
    fun printHostname() { print(hostname) }
}

class Connection(val host: Host, val port: Int) {

    fun printPort() { print(port) }

    fun Host.printConnectionString() {
        printHostname()   // calls Host.printHostname()
        print(":")
        printPort()   // calls Connection.printPort()

        toString() // calls Host.toString()
        this@Connection.toString() // calls Connection.toString()
    }

    fun connect() {
        /*...*/
        host.printConnectionString()   // calls the extension function
    }
}