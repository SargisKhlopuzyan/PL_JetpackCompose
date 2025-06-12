package com.sargis.khlopuzyan.kotlinlang.collections

/**
The read-only collection types are covariant.
This means that, if a Rectangle class inherits from Shape,
you can use a List<Rectangle> anywhere the List<Shape> is required.
In other words, the collection types have the same subtyping relationship as the element types.
Maps are covariant on the value type, but not on the key type.
 */

fun main() {

    mutableListOf<String>("", "")
    val rectangles: List<Rectangle> = listOf<Rectangle>(Rectangle(), Rectangle())
    var shapes: List<Shape> = listOf<Shape>(Shape(), Shape())
    shapes = rectangles
    val items: List<Shape> = listOf<Rectangle>(Rectangle(), Rectangle())

    val mapOfKV: Map<K, V> = mapOf<K, V>(Pair(K(), V()), Pair(K(), V()))
    var mapOfKBaseV: Map<K, BaseV> = mapOf<K, BaseV>(Pair(K(), BaseV()), Pair(K(), BaseV()))
    mapOfKBaseV = mapOfKV

//    val maps = Map<BaseK, BaseV> = mapOf<K, V>(Pair(K(), BaseV()), Pair(K(), BaseV())) // Error
    val maps: Map<K, BaseV> = mapOf<K, V>(Pair(K(), V()), Pair(K(), V()))
}

open class Shape
class Rectangle : Shape()

open class BaseK
open class BaseV
class K : BaseK()
class V : BaseV()