package com.juansanz.themovieapp.data.server

import com.google.gson.annotations.SerializedName

data class RemoteResult(
    val page: Int,
    val results: List<RemoteMovie>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int,
)

/*data class RemoteMovie(
    val adult: Boolean,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("genre_ids") val genreIds: List<Int>,
    val id: Int,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("original_title") val originalTitle: String,
    val overview: String,
    val popularity: Double,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("release_date") val releaseDate: String,
    val title: String,
    val video: Boolean,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Int
)*/

data class RemoteMovie(
    val id: Long,
    val title: String,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Int,
    val status: String,
    @SerializedName("release_date") val releaseDate: String,
    val revenue: Long,
    val runtime: Int,
    val adult: String,
    @SerializedName("backdrop_path")val backdropPath: String,
    val budget: Long,
    val homepage: String,
    @SerializedName("imdb_id") val imdbId: String,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("original_title") val originalTitle: String,
    val overview: String,
    val popularity: Double,
    @SerializedName("poster_path") val posterPath: String,
    val tagline: String,
    val genres: List<String>,
    @SerializedName("production_companies") val productionCompanies: List<String>,
    @SerializedName("production_countries") val productionCountries: List<String>,
    @SerializedName("spoken_languages") val spokenLanguages: List<String>,
    val keywords: List<String>,
)
