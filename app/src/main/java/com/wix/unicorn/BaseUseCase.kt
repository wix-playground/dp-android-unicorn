package com.wix.unicorn

import com.wix.unicorn.base.data.Either
import com.wix.unicorn.base.data.Failure
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


abstract class BaseUseCase<out Type, in Params> where Type : Any {

    abstract suspend fun run(params: Params): Either<Failure, Type>

    suspend operator fun invoke(params: Params, onResult: (Either<Failure, Type>) -> Unit = {}) {
        val job = withContext(Dispatchers.IO) { run(params) }
        withContext(Dispatchers.Main) { onResult.invoke(job) }
    }
}