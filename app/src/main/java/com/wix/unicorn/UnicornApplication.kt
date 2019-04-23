package com.wix.unicorn

import android.app.Application
import com.wix.network.Network
import com.wix.unicorn.feature.auth.AuthViewModel
import com.wix.unicorn.feature.movie.FetchMoviesUseCase
import com.wix.unicorn.feature.movie.MoviesViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class UnicornApplication : Application() {

    companion object {
        const val DEFAULT_NAMESPACE = "default"
    }



    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@UnicornApplication)
            modules(
                appModule,
                Network().modules
            )
        }
    }

    val appModule = module {
        single { FetchMoviesUseCase(get()) }
        viewModel { MoviesViewModel(get()) }
        viewModel { AuthViewModel() }
    }

}