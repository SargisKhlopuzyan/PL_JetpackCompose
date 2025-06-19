package com.sargis.khlopuzyan.kotlinlang

import kotlin.random.Random

/**
 * let, run, with, apply, also
 * */

fun main() {
    val anyClass = randomFunction()
    println("anyClass: $anyClass")

    val letResult: Int? = anyClass?.let {
        println("let -> it: $it")
        it.letVar = Scopes.LET
        17
    }

    val runResult: Int? = anyClass?.run {
        println("run -> this: $this")
//        this.x = 10
        this.runVar = Scopes.RUN
        17
    }

    val withResult: Int? = with(anyClass) {
        println("with -> this: $this")
//        this?.x = 10
        this?.withVar = Scopes.WITH
        17
    }

    val applyResult: AnyClass? = anyClass?.apply {
        println("apply -> this: $this")
        this.applyVar = Scopes.APPLY
    }

    val alsoResult: AnyClass? = anyClass?.also {
        println("also -> it: $it")
        it.alsoVar = Scopes.ALSO
    }

    println("anyClass: $anyClass")

}

fun randomFunction(): AnyClass? {
    return if (Random.nextBoolean()) AnyClass() else null
}

class AnyClass(val a: String = "abc") {
    var letVar: Scopes? = null
    var runVar: Scopes? = null
    var withVar: Scopes? = null
    var applyVar: Scopes? = null
    var alsoVar: Scopes? = null

    override fun toString(): String {
        return "letVar: $letVar runVar: $runVar withVar: $withVar applyVar: $applyVar alsoVar: $alsoVar hashCode: ${hashCode()}"
    }
}

enum class Scopes {
    LET, RUN, WITH, APPLY, ALSO
}