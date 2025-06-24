package com.sargis.khlopuzyan.kotlinlang.inlineFunctions

private fun main() {
    val x = MyTreeNode<String>(
        null,
        MyTreeNode<String>(null, MyTreeNode(null, MyTreeNode<String>(null, null)))
    ).findParentOfType(String::class.java)
}

fun <T> MyTreeNode<T>.findParentOfType(clazz: Class<T>): T? {
    var p = this.children
    while (p != null && !clazz.isInstance(p)) {
        p = p.parent
    }
    @Suppress("UNCHECKED_CAST")
    return p as T?
}

class MyTreeNode<T>(val children: MyTreeNode<T>?, val parent: MyTreeNode<T>?)