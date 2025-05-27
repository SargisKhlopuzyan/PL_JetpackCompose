package com.sargis.khlopuzyan.presentation.unit_test

import com.google.common.truth.Truth
import org.junit.Test

class RegistrationUtilTest {
    @Test
    fun `empty username returns false`() {
        val result = RegistrationUtil.validateRegistrationInput(
            "",
            "123",
            "123"
        )
        Truth.assertThat(result).isFalse()
    }

    @Test
    fun `valid username and correctly repeated password returns true`() {
        val result = RegistrationUtil.validateRegistrationInput(
            "Sargis",
            "123",
            "123"
        )
        Truth.assertThat(result).isTrue()
    }

    @Test
    fun `username already exists return false`() {
        val result = RegistrationUtil.validateRegistrationInput(
            "Carl",
            "123",
            "123"
        )
        Truth.assertThat(result).isFalse()
    }

    @Test
    fun `empty password returns false`() {
        val result = RegistrationUtil.validateRegistrationInput(
            "Carl",
            "",
            ""
        )
        Truth.assertThat(result).isFalse()
    }

    @Test
    fun `incorrectly confirmed password returns false`() {
        val result = RegistrationUtil.validateRegistrationInput(
            "Carl",
            "123",
            "123456"
        )
        Truth.assertThat(result).isFalse()
    }

    @Test
    fun `less then 2 digits password returns false`() {
        val result = RegistrationUtil.validateRegistrationInput(
            "Carl",
            "123456",
            "12abcd"
        )
        Truth.assertThat(result).isFalse()
    }
}