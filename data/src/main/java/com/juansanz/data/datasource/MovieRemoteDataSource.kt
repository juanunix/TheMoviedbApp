package com.juansanz.data.datasource

import arrow.core.Either
import com.juansanz.domain.Error
import com.juansanz.domain.Movie

interface MovieRemoteDataSource {
    suspend fun findAllMovies(): Either<Error, List<Movie>>

    suspend fun findPopularMovies(title: String): Either<Error, List<Movie>>

    suspend fun findById(id: String): Either<Error, Movie>
}
