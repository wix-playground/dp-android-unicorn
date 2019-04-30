package com.wix.unicorn.core.service.repository

import android.util.Log
import com.wix.unicorn.core.domain.model.UserProfile
import com.wix.unicorn.nertwork.LoginRequest
import com.wix.unicorn.nertwork.dto.result.LoginResult
import com.wix.unicorn.optionals.Either
import com.wix.unicorn.optionals.Failure

class LoginRepository(val loginRequest: LoginRequest) {
    val TAG = "login"
    fun login(email: String, password: String): Either<Failure, UserProfile> {
        val result: Either<Failure, LoginResult> = loginRequest.login(email, password)
        when (result) {
            is Either.Success -> {

                Log.w(TAG, "Success")
                return Either.Success(UserProfile(result.b.email, result.b.guid))
            }
            is Either.Error -> {
                Log.e(TAG, "Error")
                return result
            }
        }

    }
}