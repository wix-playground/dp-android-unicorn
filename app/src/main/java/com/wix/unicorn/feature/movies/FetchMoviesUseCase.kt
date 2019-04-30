package com.wix.unicorn.feature.movies

import com.wix.unicorn.core.domain.model.Movie
import com.wix.unicorn.core.domain.repository.MoviesRepository
import com.wix.unicorn.optionals.Either
import com.wix.unicorn.optionals.Failure
import com.wix.unicorn.base.BaseUseCase


class FetchMoviesUseCase(private val moviesRepository: MoviesRepository) :
    com.wix.unicorn.base.BaseUseCase<List<Movie>, com.wix.unicorn.base.BaseUseCase.None>() {

    override suspend fun work(params: None): Either<Failure, List<Movie>> {
        // some logic could be done here
        // like parsing success/failure
        return moviesRepository.receiveMovies()
    }
}