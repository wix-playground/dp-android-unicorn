package com.wix.unicorn.feature.auth

import com.wix.unicorn.base.BaseUseCase
import com.wix.unicorn.core.domain.model.UserProfile
import com.wix.unicorn.core.service.repository.LoginRepository
import com.wix.unicorn.optionals.Either
import com.wix.unicorn.optionals.Failure

class GetProfileUseCase(private val loginRepository: LoginRepository) : BaseUseCase<UserProfile, Unit>() {
    override suspend fun work(params: Unit): Either<Failure, UserProfile> {
        val profile = loginRepository.getUserProfile()
        return if (profile == null)
            Either.Error(Failure.UserNotAutorized)
        else
            Either.Success(UserProfile(profile.email, profile.guid))
    }
}