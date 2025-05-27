package com.sargis.khlopuzyan.presentation.unit_test

import com.google.common.truth.Truth
import org.junit.Assert.*
import org.junit.Test

class StringUtilTest {
    @Test
    fun `count of opening braces and closing braces should be the same`() {
        val wrongText = ")(___(((abc)ssa(aas)(s(sasa))__)(abc))"
        val correctText = "(abc)ssa(aas)(s(sasa))"
        val result = StringUtil.checkBraces(correctText)
        Truth.assertThat(result).isTrue()
    }

    @Test
    fun `in any position opening braces count should be not less then closing braces count`() {
        val wrongText = "((abc)ssa)("
        val correctText = "(abc)ssa(aas)(s(sasa))"
        val result = StringUtil.checkBraces(wrongText)
        Truth.assertThat(result).isFalse()
    }

    @Test
    fun `first brace should not be closing brace`() {
        val wrongText = ")(_((abc)ssa)"
        val correctText = "(abc)ssa(aas)(s(sasa))"
        val result = StringUtil.checkBraces(wrongText)
        Truth.assertThat(result).isFalse()
    }

    @Test
    fun `last brace should not be starting brace`() {
        val wrongText = "(abc)ssa)("
        val correctText = "(abc)ssa(aas)(s(sasa))"
        val result = StringUtil.checkBraces(wrongText)
        Truth.assertThat(result).isFalse()
    }

    @Test
    fun `count of opening braces and closing braces should be the same and the order is important`() {
        val wrongText = ")(___(((abc)ssa(aas)(s(sasa))__)(abc))"
        val correctText = "((a)bc)ssa(aas)(s(sasa)..)()"
        val result = StringUtil.checkBraces(correctText)
        Truth.assertThat(result).isTrue()
    }

}