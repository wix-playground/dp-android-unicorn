package com.wix.unicorn.feature.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wix.unicorn.core.domain.model.Movie
import kotlinx.coroutines.launch

class MoviesViewModel(private val fetchMoviesUseCase: FetchMoviesUseCase) : com.wix.unicorn.base.BaseViewModel() {

    val moviesStream: MutableLiveData<List<Movie>> = MutableLiveData()

    fun onResume() {
        viewModelScope.launch(exceptionHandler) {
            fetchMoviesUseCase(params = com.wix.unicorn.base.BaseUseCase.None()) {
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