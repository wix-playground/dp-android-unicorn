package com.wix.unicorn.core.domain.repository

import com.wix.unicorn.core.domain.model.Movie
import com.wix.unicorn.optionals.Either
import com.wix.unicorn.optionals.Failure

interface MoviesRepository {
    suspend fun receiveMovies(): Either<Failure, List<Movie>>
}