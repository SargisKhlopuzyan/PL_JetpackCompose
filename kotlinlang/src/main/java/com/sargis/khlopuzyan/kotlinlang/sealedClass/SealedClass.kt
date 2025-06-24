package com.sargis.khlopuzyan.kotlinlang.sealedClass

private fun main() {
    val error: Error = FileReadError(file = "file")
    when(error) {
        is DatabaseError -> {}
        is FileReadError -> {}
        RuntimeError -> {}
        ErrorType.FILE_ERROR -> {}
        ErrorType.DATABASE_ERROR -> {}
    }

    val error2: IOError = FileReadError(file = "file")
    when(error2) {
        is DatabaseError -> {}
        is FileReadError -> {}
    }
}

// Create a sealed interface
sealed interface Error
// Create a sealed class that implements sealed interface Error
sealed class IOError /*protected *//*private*//* constructor(val code: Int)*/: Error {
    // A sealed class constructor has protected visibility by default. It's visible inside this class and its subclasses
    private constructor() { /*...*/ }

    // Private constructor, visible inside this class only.
    // Using a private constructor in a sealed class allows for even stricter control over instantiation, enabling specific initialization procedures within the class.
    protected constructor(description: String): this() { /*...*/ }

    // This will raise an error because public and internal constructors are not allowed in sealed classes
    // public constructor(code: Int): this() {}
}

// Define subclasses that extend sealed class 'IOError'
class FileReadError(val file: String): IOError("401")
//class FileReadError2(val file: String): IOError()
class DatabaseError(val source: String): IOError("402")

// Create a singleton object implementing the 'Error' sealed interface
object RuntimeError : Error

//

enum class ErrorType : Error {
    FILE_ERROR, DATABASE_ERROR
}