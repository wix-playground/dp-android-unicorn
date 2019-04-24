package com.wix.unicorn.optionals

/**
 * Represents a value of one of two possible types (a disjoint union).
 * Instances of [Either] are either an instance of [Error] or [Success].
 * FP Convention dictates that [Error] is used for "failure"
 * and [Success] is used for "success".
 *
 * @see Error
 * @see Success
 */
sealed class Either<out L, out R> {
    /** * Represents the left side of [Either] class which by convention is a "Failure". */
    data class Error<out L>(val a: L) : Either<L, Nothing>()

    /** * Represents the right side of [Either] class which by convention is a "Success". */
    data class Success<out R>(val b: R) : Either<Nothing, R>()

    val isRight get() = this is Success<R>
    val isLeft get() = this is Error<L>

    fun <L> left(a: L) = Error(a)
    fun <R> right(b: R) = Success(b)

    fun either(fnL: (L) -> Any, fnR: (R) -> Any): Any =
        when (this) {
            is Error -> fnL(a)
            is Success -> fnR(b)
        }
}