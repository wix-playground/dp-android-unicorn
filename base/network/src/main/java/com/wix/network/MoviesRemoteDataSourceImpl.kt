package com.wix.network

import com.wix.dto.MovieApi
import com.wix.unicorn.optionals.Either
import com.wix.unicorn.optionals.Failure

class MoviesRemoteDataSourceImpl(val api: MoviesApi) : MoviesRemoteDataSource {
    override suspend fun receiveMovies(): Either<Failure, List<MovieApi>> {
        val response = api.movies().execute()
        return when {
            response.isSuccessful -> Either.Success(response.body() ?: emptyList())
            else -> Either.Error(Failure.NetworkConnection)

        }
    }

}