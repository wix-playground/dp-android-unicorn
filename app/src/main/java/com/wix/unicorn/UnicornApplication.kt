package com.wix.unicorn

import android.app.Application
import com.wix.unicorn.core.coreModule
import com.wix.unicorn.feature.movies.movieModule
import com.wix.unicorn.feature.splash.splashModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class UnicornApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@UnicornApplication)
            modules(
                presentationModule,
                coreModule,
                splashModule,
                movieModule
            )
        }
    }


}