package com.sargis.khlopuzyan.kotlinlang.dataClasses

private fun main() {
    val user = DataClass("Sargis", 36)
    user.equals(null)
    user.hashCode()
    user.toString()
    user.component1()
    user.component2()
    user.copy()
    user.copy(name = "Saqo")
//    user.copy(surname = "") // compile error
}

/**
 * The primary constructor must have at least one parameter.
 *
 * All primary constructor parameters must be marked as val or var.
 *
 * Data classes can't be abstract, open, sealed, or inner.
 * */
data class DataClass(val name: String, val age: Int) : Man(), Language {

    val surname = "Khlopuzyan"

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    override fun toString(): String {
        return super.toString()
    }

    override fun language(): String {
        return "Armenian"
    }
}

open class Man {
    open fun gender(isMan: Boolean): String {
        return if (isMan) "Man" else "Female"
    }
}

interface Language {
    fun language(): String
}