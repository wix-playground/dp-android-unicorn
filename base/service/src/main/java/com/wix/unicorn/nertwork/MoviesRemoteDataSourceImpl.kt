package com.wix.unicorn.nertwork

import com.wix.unicorn.nertwork.dto.MovieApi
import com.wix.unicorn.optionals.Either
import com.wix.unicorn.optionals.Failure

class MoviesRemoteDataSourceImpl(private val api: MoviesApi) :
    MoviesRemoteDataSource {
    override suspend fun receiveMovies(): Either<Failure, List<MovieApi>> {
        val response = api.movies().execute()
        return when {
            response.isSuccessful -> Either.Success(response.body() ?: emptyList())
            else -> Either.Error(Failure.NetworkConnection)

        }
    }

}