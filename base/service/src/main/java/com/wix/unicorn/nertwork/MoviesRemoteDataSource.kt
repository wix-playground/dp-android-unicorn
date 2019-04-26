package com.wix.unicorn.nertwork

import com.wix.unicorn.nertwork.dto.MovieApi
import com.wix.unicorn.optionals.Either
import com.wix.unicorn.optionals.Failure

interface MoviesRemoteDataSource {
    suspend fun receiveMovies(): Either<Failure, List<MovieApi>>
}