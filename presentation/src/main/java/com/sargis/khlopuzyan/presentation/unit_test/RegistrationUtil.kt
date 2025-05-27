package com.sargis.khlopuzyan.presentation.unit_test

object RegistrationUtil {

    private val existingUsers = listOf("Peter", "Carl")

    /**
     * the input is not valid if...
     * ...the username/password is empty
     * ...the username is already taken
     * ...the confirmed password is not the same as the real password
     * ...the password contains less then 2 digits
     * */
    fun validateRegistrationInput(
        username: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        return when {
            username.isEmpty() or password.isEmpty() -> false
            username in existingUsers -> false
            password != confirmPassword -> false
            password.count { it.isDigit() } < 2 -> false
            else -> true
        }
    }
}