package com.wix.unicorn.feature.movies

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.wix.unicorn.R
import com.wix.unicorn.core.domain.model.Movie
import com.wix.unicorn.optionals.Failure

import kotlinx.android.synthetic.main.activity_movies.*
import org.koin.android.viewmodel.ext.android.viewModel

class MoviesActivity : com.wix.unicorn.base.BaseActivity() {

    override val layoutId: Int get() = R.layout.activity_movies

    private val model: MoviesViewModel by viewModel()
    private val adapter: MoviesAdapter get() = list.adapter as MoviesAdapter

    private val list get() = a_movies_list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        list.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        list.adapter = MoviesAdapter()

        model.moviesStream.observe(this, Observer<List<Movie>> { adapter.collection = it })

    }

    override fun onResume() {
        super.onResume()
        model.onResume()
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.NetworkConnection -> Toast.makeText(
                this,
                getString(R.string.network_error),
                Toast.LENGTH_SHORT
            ).show()
            is Failure.ServerError -> {
            }
        }
    }
}