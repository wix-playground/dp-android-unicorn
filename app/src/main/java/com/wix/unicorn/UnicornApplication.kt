package com.wix.unicorn

import android.app.Application
import com.wix.network.Network
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class UnicornApplication : Application() {

    companion object {
        const val DEFAULT_NAMESPACE = "default"
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@UnicornApplication)
            modules(Network(DEFAULT_NAMESPACE).modules)
        }
    }

}