package com.wix.unicorn.feature.auth

import com.wix.unicorn.base.BaseUseCase
import com.wix.unicorn.core.domain.model.UserProfile
import com.wix.unicorn.core.service.repository.LoginRepository
import com.wix.unicorn.optionals.Either
import com.wix.unicorn.optionals.Failure

class WixLoginUseCase(val loginRepository: LoginRepository) : BaseUseCase<UserProfile, WixLoginUseCase.Params>() {
    override suspend fun work(params: Params): Either<Failure, UserProfile> {
        return loginRepository.login(params.email, params.password)
    }

    class Params(val email: String, val password: String)
}
