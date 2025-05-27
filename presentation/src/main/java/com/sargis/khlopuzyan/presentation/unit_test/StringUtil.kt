package com.sargis.khlopuzyan.presentation.unit_test

object StringUtil {
    /**
     * Check if the braces are set correctly
     * e.g. (a*b)) should return false
     * */
    fun checkBraces(text: String): Boolean {
        var openingBracesCount = 0
        var closingBracesCount = 0

        text.forEachIndexed { index, letter ->
            if (letter == '(') {
                openingBracesCount++
            } else if (letter == ')') {
                if (openingBracesCount == 0) {
                    return false
                }
                closingBracesCount++
                if (closingBracesCount > openingBracesCount) {
                    return false
                }
            }
        }
        return openingBracesCount == closingBracesCount
    }
}