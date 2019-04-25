package com.wix.unicorn.movie

import com.wix.unicorn.core.navigation.NavigateToMovies
import com.wix.unicorn.movie.navigation.NavigateToMoviesImpl
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val movieModule = module {
    single { FetchMoviesUseCase(get()) }
    single<NavigateToMovies> { NavigateToMoviesImpl() }
    viewModel { MoviesViewModel(get()) }
}