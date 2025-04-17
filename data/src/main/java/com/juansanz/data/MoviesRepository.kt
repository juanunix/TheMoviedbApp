package com.juansanz.data

import arrow.core.Either
import com.juansanz.data.datasource.MovieRemoteDataSource
import com.juansanz.domain.Error
import com.juansanz.domain.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class MoviesRepository(
    private val remoteDataSource: MovieRemoteDataSource,
) {
    private val _popularMovies = MutableStateFlow<List<Movie>>(emptyList())
    val popularMovies: Flow<List<Movie>> = _popularMovies

    fun findById(id: Int): Flow<Movie> = remoteDataSource.findById(id)

    suspend fun requestPopularMovies(): Error? {
        val result: Either<Error, List<Movie>> =
            remoteDataSource.findPopularMovies(title = "Inception")
        result.fold(
            ifLeft = { return it },
            ifRight = { movieList ->
                _popularMovies.value = movieList
            },
        )
        return null
    }
}
