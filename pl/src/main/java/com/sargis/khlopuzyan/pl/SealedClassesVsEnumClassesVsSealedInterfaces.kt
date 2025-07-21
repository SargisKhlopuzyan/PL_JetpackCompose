package com.sargis.khlopuzyan.pl

/**
 * 1) sealed class can be inherited only in module where they are declared
 * 2) sealed class with objects are the same as enum with no param
 * 3) enum class items are const, sealed class data class are instances of that class
 * 4) enum class can't be inherited
 * 5) enum class constants can have the same params, but sealed classes can have both versions
 * */
sealed class HttpError {

    object Unauthorized : HttpError() {
        override fun doSomething1() {

        }
    }

    object NotFound : HttpError() {
        override fun doSomething1() {

        }
    }

    data class UnauthorizedWithParam(val code: Int) : HttpError() {
        override fun doSomething1() {

        }
    }

    abstract fun doSomething1()

    fun doSomething() {

    }
}

enum class HttpErrorEnum(val code: Int) {
    Unauthorized(1) {
        override fun doSomething1() {

        }
    },
    NotFound(2) {
        override fun doSomething1() {
        }
    };

    abstract fun doSomething1()

    fun doSomething() {

    }
}