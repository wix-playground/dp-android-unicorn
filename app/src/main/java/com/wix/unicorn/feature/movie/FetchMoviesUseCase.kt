package com.wix.unicorn.feature.movie

import com.wix.base.Either
import com.wix.base.Failure
import com.wix.dto.Movie
import com.wix.network.Api
import com.wix.unicorn.base.BaseUseCase

class FetchMoviesUseCase(private val service: Api) : BaseUseCase<List<Movie>, BaseUseCase.None>() {

    override suspend fun work(params: None): Either<Failure, List<Movie>> {
        val response = service.movies().execute()
        return when {

            response.isSuccessful ->
                Either.Success(response.body() ?: emptyList())

            else ->
                Either.Error(Failure.NetworkConnection)

        }
    }
}