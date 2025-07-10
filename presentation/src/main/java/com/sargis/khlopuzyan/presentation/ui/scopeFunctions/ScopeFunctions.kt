package com.sargis.khlopuzyan.presentation.ui.scopeFunctions

fun main() {
    val customClass = CustomClass()

    // 1) run
    val resultRun = customClass.run {
        this
        12
    }

    val resultRun2 = run {
        12
    }
    // resultRun & resultRun2 are Int

    // ****************************** //
    // 2)
    val resultWith = with(customClass) {
        this
        12
    }
    // resultWith is Int

    // ****************************** //
    // 3) let

    val resultLet = customClass.let {
        it
        12
    }

    // resultLet is Int

    // ****************************** //
    // 4)

    val resultAlso = customClass.also {
        it
        12
    }
    // resultAlso is CUSTOM CLASS


    // ****************************** //
    // 5)
    val resultApply = customClass.apply {
        this
        12
    }
    // resultApply is CUSTOM CLASS


}

open class CustomClass {
    fun getAge() {

    }
}