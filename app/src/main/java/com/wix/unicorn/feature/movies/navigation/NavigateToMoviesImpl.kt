package com.wix.unicorn.feature.movies.navigation

import android.content.Context
import android.content.Intent
import com.wix.unicorn.core.navigation.NavigateToMovies
import com.wix.unicorn.feature.movies.MoviesActivity
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppScreen

class NavigateToMoviesImpl : NavigateToMovies {
    override fun go(context: Context) {
        context.startActivity(Intent(context, MoviesActivity::class.java))
    }
}

class MoviesScreen : SupportAppScreen() {
    override fun getActivityIntent(context: Context?): Intent {
        return Intent(context, MoviesActivity::class.java)
    }
}