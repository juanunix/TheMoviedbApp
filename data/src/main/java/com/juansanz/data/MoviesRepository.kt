package com.juansanz.data

import arrow.core.Either
import com.juansanz.data.datasource.MovieLocalDataSource
import com.juansanz.data.datasource.MovieRemoteDataSource
import com.juansanz.domain.Error
import com.juansanz.domain.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flow

class MoviesRepository(
    private val remoteDataSource: MovieRemoteDataSource,
    private val localDataSource: MovieLocalDataSource,
) {
    private val _popularMovies = MutableStateFlow<List<Movie>>(emptyList())
    val popularMovies: Flow<List<Movie>> get() = _popularMovies.asSharedFlow()

    suspend fun findById(id: String): Flow<Movie> =
        flow {
            val result: Either<Error, Movie> = remoteDataSource.findById(id)
            result.fold(
                ifLeft = { it },
                ifRight = { movie ->
                    emit(movie)
                },
            )
        }

    suspend fun requestPopularMovies(): Error? {
        val result: Either<Error, List<Movie>> = remoteDataSource.findAllMovies()
        result.fold(
            ifLeft = { return it },
            ifRight = { movieList ->
                _popularMovies.value = movieList
            },
        )
        return null
    }

    suspend fun switchFavorite(movie: Movie): Error? {
        val updatedMovie = movie.copy(favorite = !movie.favorite)
        return localDataSource.save(listOf(updatedMovie))
    }
}
