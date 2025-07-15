package com.sargis.khlopuzyan.liveCoding

class Task(var id: Long, val name: String)

//val list = listOf(1, 3, 5)
val list = mutableListOf(1, 3, 5)
//val tasks = HashSet<Task>()
val tasks = mutableSetOf<Task>()

private fun main() {
    val task1 = Task(1, "Zadacha")
    val task2 = Task(1, "Zadacha")

    tasks.add(task1)
    tasks.add(task2)

    list.add(7)
    list.forEvery {
        if (it == 3) {
            return
        }
        println("$it")
    }

    println("tasks contains ${tasks.size} elements")
    println("Done!")
}

inline fun </*reified*/ T> List<T>.forEvery(itemAction: (T) -> Unit) {
//    list.reversed().forEach {
    this.reversed().forEach {
        itemAction(it)
    }
}


/**
val list = listOf(1, 3, 5)
val tasks = HashSet<Task>()

fun main() {
val task1 = Task(1, "Zadacha")
val task2 = Task(1, "Zadacha")

tasks.add(task1)
tasks.add(task2)

list.add(7)
list.forEvery {
if (it == 3) {
return
}
}
println("tasks contains ${tasks.size} elements")
println("Done!")
}

inline fun <reified T> List<T>.forEvery(itemAction: (T) -> Unit) {
list.reversed().forEach {
itemAction(it)
}
}
 */