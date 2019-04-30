package com.wix.unicorn.feature.auth

import com.wix.unicorn.base.BaseUseCase
import com.wix.unicorn.core.service.repository.LoginRepository
import com.wix.unicorn.optionals.Either
import com.wix.unicorn.optionals.Failure

class LogoutUseCase(val loginRepository: LoginRepository) : BaseUseCase<Unit, Unit>() {
    override suspend fun work(params: Unit): Either<Failure, Unit> {
        return loginRepository.logout()
    }
}
