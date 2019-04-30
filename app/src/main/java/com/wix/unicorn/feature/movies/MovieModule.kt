package com.wix.unicorn.feature.movies

import com.wix.unicorn.core.navigation.NavigateToMovies
import com.wix.unicorn.feature.movies.navigation.NavigateToMoviesImpl
import com.wix.unicorn.utils.imageloader.GlideLoadDelegate
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val movieModule = module {
    single { FetchMoviesUseCase(get()) }
    single<NavigateToMovies> { NavigateToMoviesImpl() }
    viewModel { MoviesViewModel(get()) }
    scope(named("Movie")) { GlideLoadDelegate() }
}