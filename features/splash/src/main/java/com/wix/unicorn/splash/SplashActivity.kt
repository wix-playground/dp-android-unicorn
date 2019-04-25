package com.wix.unicorn.splash

import android.os.Bundle
import com.wix.unicorn.core.navigation.NavigateToMovies
import com.wix.unicorn.presentation.base.BaseActivity
import org.koin.android.ext.android.inject


class SplashActivity(
    override val layoutId: Int = R.layout.activity_splash
) : BaseActivity() {

    private val navigateToMovies: NavigateToMovies by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigateToMovies.go(this)
    }

}
