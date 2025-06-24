package com.sargis.khlopuzyan.kotlinlang.functions

private fun main() {
    val callback: (Int, String, Float) -> String = { a: Int, b: String, c: Float ->
        ""
    }
//    function1(x = 10, y = "20", z = 1.0f) {}
//    function1(10, "20", 1.0f) {
    function1(10, "20", 1.0f, callback = callback)

    val items = customList(1, 2, 3)

}

private fun function1(
    x: Int,
    y: String,
    z: Float = 1.0f,
    callback: (a: Int, b: String, c: Float) -> String,
): String {
    return callback(x, y, z)
}

//inline fun <reified T> customList(vararg items: T): List<T> {
fun <T> customList(vararg items: T): List<T> {
    return listOf<T>(*items)
}