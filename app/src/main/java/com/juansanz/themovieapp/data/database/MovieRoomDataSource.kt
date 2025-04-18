package com.juansanz.themovieapp.data.database

import com.juansanz.data.datasource.MovieLocalDataSource
import com.juansanz.domain.Error
import com.juansanz.domain.Movie
import com.juansanz.themovieapp.data.tryCall
import com.juansanz.themovieapp.data.database.Movie as DbMovie

class MovieRoomDataSource(
    private val movieDao: MovieDao,
) : MovieLocalDataSource {
    override val movies: List<Movie> = movieDao.getAll().map { it.toDomainModel() }

    override suspend fun isEmpty(): Boolean = movieDao.movieCount() == 0

    override fun findById(id: Int): Movie = movieDao.findById(id).toDomainModel()

    override suspend fun save(movies: List<Movie>): Error? =
        tryCall {
            movieDao.insertMovies(movies.fromDomainModel())
        }.fold(
            ifLeft = { it },
            ifRight = { null },
        )
}

private fun List<DbMovie>.toDomainModel(): List<Movie> = map { it.toDomainModel() }

private fun DbMovie.toDomainModel(): Movie =
    Movie(
        id,
        title,
        voteAverage,
        voteCount,
        status,
        releaseDate,
        revenue,
        runtime,
        adult,
        backdropPath,
        budget,
        homepage,
        imdbId,
        originalLanguage,
        originalTitle,
        overview,
        popularity,
        posterPath,
        tagline,
        emptyList(),
        emptyList(),
        emptyList(),
        emptyList(),
        emptyList(),
        favorite,
    )

private fun List<Movie>.fromDomainModel(): List<DbMovie> = map { it.fromDomainModel() }

private fun Movie.fromDomainModel(): DbMovie =
    DbMovie(
        id,
        title,
        voteAverage,
        voteCount,
        status,
        releaseDate,
        revenue,
        runtime,
        adult,
        backdropPath,
        budget,
        homepage,
        imdbId,
        originalLanguage,
        originalTitle,
        overview,
        popularity,
        posterPath,
        tagline,
        genres.toString(),
        productionCompanies.toString(),
        productionCountries.toString(),
        spokenLanguages.toString(),
        keywords.toString(),
        favorite,
    )
