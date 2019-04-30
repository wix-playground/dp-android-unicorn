package com.wix.unicorn.core.service.repository

import android.util.Log
import com.wix.unicorn.core.domain.model.UserProfile
import com.wix.unicorn.database.UserProfileStorage
import com.wix.unicorn.nertwork.LoginRequest
import com.wix.unicorn.nertwork.dto.result.LoginResult
import com.wix.unicorn.optionals.Either
import com.wix.unicorn.optionals.Failure

class LoginRepository(
    private val loginRequest: LoginRequest,
    private val userLocalDataSource: UserProfileStorage
) {

    val TAG = "login"
    private fun _login(email: String, password: String): Either<Failure, LoginResult> {
        return loginRequest.login(email, password)
    }

    fun login(email: String, password: String): Either<Failure, UserProfile> {
        val result = _login(email, password)
        return when (result) {
            is Either.Success -> {
                val profile = UserProfileStorage.UserProfile(
                    email,
                    password,
                    result.b.guid,
                    result.b.uaToken
                )
                userLocalDataSource.profile = profile
                return Either.Success(UserProfile(profile.email, profile.guid))
            }
            is Either.Error -> {
                Log.e(TAG, "Error")
                result
            }
        }
    }

    fun getUserProfile(): UserProfile? = userLocalDataSource.profile?.let { UserProfile(it.email, it.guid) }


}