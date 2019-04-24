package com.wix.unicorn.core.service.repository

import com.wix.network.MoviesRemoteDataSource
import com.wix.unicorn.core.domain.model.Movie
import com.wix.unicorn.core.domain.repository.MoviesRepository
import com.wix.unicorn.database.LocalDataSource
import com.wix.unicorn.optionals.Either
import com.wix.unicorn.optionals.Failure

class MoviesRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: MoviesRemoteDataSource
) : MoviesRepository {
    override suspend fun receiveMovies(): Either<Failure, List<Movie>> {
        val movies = remoteDataSource.receiveMovies()
        return when (movies) {
            is Either.Error -> Either.Error(movies.a)
            is Either.Success -> Either.Success(movies.b.map { Movie(it.id, it.poster) })
        }
    }

}