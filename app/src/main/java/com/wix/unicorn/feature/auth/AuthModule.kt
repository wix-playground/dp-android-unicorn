package com.wix.unicorn.feature.auth

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authModule = module {
    single { WixLoginUseCase(get()) }
    single { GetProfileUseCase(get()) }
    viewModel { AuthViewModel(get(), get()) }
}