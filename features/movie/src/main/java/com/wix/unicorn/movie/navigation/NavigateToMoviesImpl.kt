package com.wix.unicorn.movie.navigation

import android.content.Context
import android.content.Intent
import com.wix.unicorn.core.navigation.NavigateToMovies
import com.wix.unicorn.movie.MoviesActivity

class NavigateToMoviesImpl : NavigateToMovies {
    override fun go(context: Context) {
        context.startActivity(Intent(context, MoviesActivity::class.java))
    }
}