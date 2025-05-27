package com.sargis.khlopuzyan.presentation.unit_test

import com.google.common.truth.Truth
import org.junit.Test
import kotlin.random.Random

class FibonacciUtilTest {

    @Test
    fun `fibonacci 0-th element equals 0`() {
        val result = FibonacciUtil.fib(0)
        Truth.assertThat(result).isEqualTo(0)
    }

    @Test
    fun `fibonacci 1-th element equals 1`() {
        val result = FibonacciUtil.fib(1)
        Truth.assertThat(result).isEqualTo(1)
    }

    @Test
    fun `fibonacci -1-th element equals -1`() {
        val result = FibonacciUtil.fib(-1)
        Truth.assertThat(result).isEqualTo(-1)
    }

    @Test
    fun `fibonacci 4-th element equals 2`() {
        val result = FibonacciUtil.fib(4)
        Truth.assertThat(result).isEqualTo(2)
    }

    @Test
    fun `fibonacci n-th element equals fibonacci (n-1)-th + (n-2)-th`() {
        val n = Random.nextInt(2, 100)
        val fibN1 = FibonacciUtil.fib(n - 2)
        val fibN2 = FibonacciUtil.fib(n - 1)
        val fibN1SumN2 = fibN1 + fibN2
        val result = FibonacciUtil.fib(n)
        Truth.assertThat(result).isEqualTo(fibN1SumN2)
    }

}