package com.wix.unicorn.feature.splash

import android.os.Bundle
import com.wix.unicorn.R
import com.wix.unicorn.core.navigation.NavigateToMovies
import org.koin.android.ext.android.inject


class SplashActivity(
    override val layoutId: Int = R.layout.activity_splash
) : com.wix.unicorn.base.BaseActivity() {

    private val navigateToMovies: NavigateToMovies by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigateToMovies.go(this)
    }


}
