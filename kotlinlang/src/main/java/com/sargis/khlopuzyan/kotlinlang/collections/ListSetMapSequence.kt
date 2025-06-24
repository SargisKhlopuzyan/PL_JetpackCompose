package com.sargis.khlopuzyan.kotlinlang.collections

private fun main() {
    val list: List<String> = listOf()
    val set: Set<String> = setOf<String>("1")
    val linkedSet: LinkedHashSet<String> = linkedSetOf<String>("1")
    val hashSet: HashSet<String> = hashSetOf<String>("1")


    val oddNumbers = generateSequence(1) { it + 2 } // `it` is the previous element
    println(oddNumbers.take(10).filter { it < 5 }.toList())


    val oddNumbers2 = sequence {
        yield(1)
        yieldAll(listOf(2, 3))
        yieldAll(generateSequence(7) { it + 2 })
//        generateSequence(7) { it + 2 } // NOT WORKING
    }
    println(oddNumbers2.take(5).toList())

}