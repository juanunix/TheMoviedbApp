package com.juansanz.themovieapp.data.database

import com.juansanz.domain.Movie
import com.juansanz.themovieapp.data.database.Movie as DbMovie

fun List<DbMovie>.toDomainModel(): List<Movie> = map { it.toDomainModel() }

fun DbMovie.toDomainModel(): Movie =
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

fun List<Movie>.fromDomainModel(): List<DbMovie> = map { it.fromDomainModel() }

fun Movie.fromDomainModel(): DbMovie =
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
