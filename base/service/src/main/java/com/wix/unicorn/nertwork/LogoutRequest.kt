package com.wix.unicorn.nertwork

import com.wix.unicorn.optionals.Either
import com.wix.unicorn.optionals.Failure

class LogoutRequest(private val api: AuthApi) {
    fun logout(): Either<Failure, Unit> {
        val result = api.logout().execute()
        val resultBody = result.body()
        return if (result.isSuccessful && resultBody != null && resultBody.success) {
            Either.Success(Unit)
        } else {
            Either.Error(Failure.NetworkConnection)
        }
    }
}