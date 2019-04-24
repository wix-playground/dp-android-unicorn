package com.wix.unicorn.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wix.unicorn.core.domain.model.Movie
import com.wix.unicorn.presentation.base.BaseUseCase
import com.wix.unicorn.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

class MoviesViewModel(private val fetchMoviesUseCase: FetchMoviesUseCase) : BaseViewModel() {

    val moviesStream: MutableLiveData<List<Movie>> = MutableLiveData()

    fun onResume() {
        viewModelScope.launch(exceptionHandler) {
            fetchMoviesUseCase(params = BaseUseCase.None()) {
                it.either(
                    ::handleFailure,
                    ::handleMovieList
                )
            }
        }
    }

    private fun handleMovieList(movies: List<Movie>) {
        moviesStream.value = movies
    }
}