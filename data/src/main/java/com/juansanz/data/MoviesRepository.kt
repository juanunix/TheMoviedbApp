package com.juansanz.data

import com.juansanz.data.datasource.LocalDataSource
import com.juansanz.data.datasource.RemoteDataSource
import com.juansanz.domain.Movie

const val THRESHOLD = 1000.0

class MoviesRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) {
    suspend fun getAllMovies(): List<Movie> {
        if (localDataSource.isEmpty()) {
            val movies = remoteDataSource.findAllMovies()
            localDataSource.saveMovies(movies)
        }

        return localDataSource.getPopularMovies()
    }

    suspend fun findById(id: Int): Movie = localDataSource.findById(id)

    suspend fun update(movie: Movie) = localDataSource.update(movie)

    suspend fun switchFavorite(movie: Movie) = localDataSource.saveMovies(listOf(movie))

    suspend fun getAllPopularMovies(): List<Movie> {
        if (localDataSource.isEmpty()) {
            val movies = remoteDataSource.findAllMovies()
            localDataSource.saveMovies(movies)
        }

        return localDataSource.getPopularMovies()
    }
}
