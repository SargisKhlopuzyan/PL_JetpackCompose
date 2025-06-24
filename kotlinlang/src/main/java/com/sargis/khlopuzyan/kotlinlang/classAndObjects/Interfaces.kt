package com.sargis.khlopuzyan.kotlinlang.classAndObjects

private fun main() {
    val employee = Employee("a", "b", 1)
    employee.name
}

interface CustomInterface {
    val valProperty: Int

    //    val valProperty2: Int = 10

    val valProperty3: Int
        get() = 10

    var varProperty: Int
//    var varProperty2: Int = 10

    var varProperty2: Int
    get() = 10
    set(value) {
//        field = value
    }
}

interface Named {
    val name: String
}

// ******************************************

interface Person : Named {
    val firstName: String
    val lastName: String

    override val name: String get() = "$firstName $lastName"
}

data class Employee(
    // implementing 'name' is not required
    override val firstName: String,
    override val lastName: String,
    val position: Int
) : Person

// ******************************************

interface A {
    fun foo() { print("A") }
    fun bar()
}

interface B {
    fun foo() { print("B") }
    fun bar() { print("bar") }
}

class C : A {
    override fun bar() { print("bar") }
}

class D : A, B {
    override fun foo() {
        super<A>.foo()
        super<B>.foo()
    }

    override fun bar() {
        super<B>.bar()
    }
}