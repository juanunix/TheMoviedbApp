package com.juansanz.themovieapp.data.server

import arrow.core.Either
import com.juansanz.data.datasource.MovieRemoteDataSource
import com.juansanz.domain.Error
import com.juansanz.domain.Movie
import com.juansanz.themovieapp.data.tryCall

class MovieServerDataSource(
    private val remoteService: RemoteService,
) : MovieRemoteDataSource {
    override suspend fun findPopularMovies(title: String): Either<Error, List<Movie>> =
        tryCall {
            remoteService
                .searchByTitle(title = title)
                .toDomainModel()
        }
}

private fun List<RemoteMovie>.toDomainModel(): List<Movie> = map { it.toDomainModel() }

private fun RemoteMovie.toDomainModel(): Movie =
    Movie(
        id = id,
        title = title,
        voteAverage = voteAverage,
        voteCount = voteCount,
        status = status,
        releaseDate = releaseDate,
        revenue = revenue,
        runtime = runtime,
        adult = adult,
        backdropPath = backdropPath.let { "https://image.tmdb.org/t/p/w780/$it" },
        budget = budget,
        homepage = homepage,
        imdbId = imdbId,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = "https://image.tmdb.org/t/p/w185/$posterPath",
        tagline = tagline,
        genres = genres,
        productionCompanies = productionCompanies,
        productionCountries = productionCountries,
        spokenLanguages = spokenLanguages,
        keywords = keywords,
        favorite = false,
    )
