package com.wix.unicorn.feature.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wix.dto.Movie
import com.wix.unicorn.base.BaseUseCase
import com.wix.unicorn.base.BaseViewModel
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