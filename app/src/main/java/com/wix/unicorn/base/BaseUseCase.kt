package com.wix.unicorn.base

import com.wix.unicorn.optionals.Either
import com.wix.unicorn.optionals.Failure
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


abstract class BaseUseCase<out Type, in Params> where Type : Any {

    abstract suspend fun work(params: Params): Either<Failure, Type>

    suspend operator fun invoke(params: Params, onResult: (Either<Failure, Type>) -> Unit = {}) {
        val job = withContext(Dispatchers.IO) { work(params) }
        onResult.invoke(job)
    }

    class None
}