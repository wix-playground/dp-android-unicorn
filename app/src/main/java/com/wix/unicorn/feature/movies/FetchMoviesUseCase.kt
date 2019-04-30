package com.wix.unicorn.feature.movies

import com.wix.unicorn.base.BaseUseCase
import com.wix.unicorn.core.domain.model.Movie
import com.wix.unicorn.core.domain.repository.MoviesRepository
import com.wix.unicorn.optionals.Either
import com.wix.unicorn.optionals.Failure


class FetchMoviesUseCase(private val moviesRepository: MoviesRepository) :
        BaseUseCase<List<Movie>, BaseUseCase.None>() {

    override suspend fun work(params: None): Either<Failure, List<Movie>> {
        // some logic could be done here
        // like parsing success/failure
        return moviesRepository.receiveMovies()
    }
}