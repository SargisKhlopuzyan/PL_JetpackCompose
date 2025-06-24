package com.sargis.khlopuzyan.kotlinlang.inlineValueClasses

private fun main() {
    // No actual instantiation of class 'Password' happens
    // At runtime 'securePassword' contains just 'String'
    val securePassword = Password("Don't try this in production")
    securePassword.sValue.uppercase()


    val name1 = Person("Kotlin", "Mascot")
    val name2 = Person("Kodee")
    name1.greet() // the `greet()` function is called as a static method
    println(name2.length) // property getter is called as a static method
}

/**
 * An inline class must have a single property initialized in the primary constructor.
 * At runtime, instances of the inline class will be represented using this single property
 * */
@JvmInline
value class Password(val sValue: String)


@JvmInline
value class Person(private val fullName: String) {
    init {
        require(fullName.isNotEmpty()) {
            "Full name shouldn't be empty"
        }
    }

    constructor(firstName: String, lastName: String) : this("$firstName $lastName") {
        require(lastName.isNotBlank()) {
            "Last name shouldn't be empty"
        }
    }

    val length: Int
        get() = fullName.length

    fun greet() {
        println("Hello, $fullName")
    }
}