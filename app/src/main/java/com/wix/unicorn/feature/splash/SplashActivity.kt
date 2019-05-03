package com.wix.unicorn.feature.splash

import android.os.Bundle
import android.os.Handler
import com.wix.unicorn.R
import com.wix.unicorn.base.BaseActivity
import com.wix.unicorn.core.navigation.NavigateToMovies
import com.wix.unicorn.feature.movies.navigation.MoviesScreen
import org.koin.android.ext.android.inject


class SplashActivity(
    override val layoutId: Int = R.layout.activity_splash
) : BaseActivity() {

    private val navigateToMovies: NavigateToMovies by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Handler().postDelayed({
            router.replaceScreen(MoviesScreen())
        }, 500)
    }


}
