package com.wix.unicorn.movie

import com.wix.unicorn.core.domain.model.Movie
import com.wix.unicorn.core.domain.repository.MoviesRepository
import com.wix.unicorn.optionals.Either
import com.wix.unicorn.optionals.Failure
import com.wix.unicorn.presentation.base.BaseUseCase


class FetchMoviesUseCase(private val moviesRepository: MoviesRepository) :
    BaseUseCase<List<Movie>, BaseUseCase.None>() {

    override suspend fun work(params: None): Either<Failure, List<Movie>> {
        // some logic could be done here
        // like parsing success/failure
        return moviesRepository.receiveMovies()
    }
}