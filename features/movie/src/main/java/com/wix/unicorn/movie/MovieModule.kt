package com.wix.unicorn.movie

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val movieModule = module {
    single { FetchMoviesUseCase(get()) }
    viewModel { MoviesViewModel(get()) }
}