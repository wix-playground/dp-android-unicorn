package com.wix.unicorn.nertwork

import com.wix.unicorn.nertwork.dto.result.LoginResult
import com.wix.unicorn.optionals.Either
import com.wix.unicorn.optionals.Failure

class LoginRequest(private val api: LoginApi) {
    fun login(email: String, password: String): Either<Failure, LoginResult> {
        val result = api.login(email, password).execute()
        val resultBody = result.body()
        return if (result.isSuccessful && resultBody != null && resultBody.success) {
            Either.Success(resultBody.payload)
        } else {
            Either.Error(Failure.NetworkConnection)
        }
    }
}