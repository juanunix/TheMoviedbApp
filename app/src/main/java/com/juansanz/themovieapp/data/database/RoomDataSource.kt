package com.juansanz.themovieapp.data.database

import com.juansanz.data.datasource.LocalDataSource
import com.juansanz.domain.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomDataSource(
    db: MovieDatabase,
) : LocalDataSource {
    private val movieDao = db.movieDao()

    override suspend fun isEmpty(): Boolean = withContext(Dispatchers.IO) { movieDao.movieCount() <= 0 }

    override suspend fun saveMovies(movies: List<Movie>) {
        withContext(Dispatchers.IO) { movieDao.insertMovies(movies.map { it.fromDomainModel() }) }
    }

    override suspend fun getPopularMovies(): List<Movie> =
        withContext(Dispatchers.IO) {
            movieDao.getAll().map { it.toDomainModel() }
        }

    override suspend fun findById(id: Int): Movie =
        withContext(Dispatchers.IO) {
            movieDao.findById(id).toDomainModel()
        }

    override suspend fun update(movie: Movie) {
        withContext(Dispatchers.IO) { movieDao.updateMovie(movie.fromDomainModel()) }
    }
}
