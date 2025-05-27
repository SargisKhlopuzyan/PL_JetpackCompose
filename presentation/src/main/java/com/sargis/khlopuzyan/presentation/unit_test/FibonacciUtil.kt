package com.sargis.khlopuzyan.presentation.unit_test

object FibonacciUtil {
    fun fib(n: Int): Int {
        when (n) {
            0 -> return 0
            1 -> return 1
            -1 -> return -1
        }

        var N = n
        if (n < 0) {
            N = -n
        }

        var a = 0
        var b = 1
        var c = 0
        (2 until N).forEach {
            c = a + b
            a = b
            b = c
        }
        return if (n > 0) c else return -1 * c
    }
}