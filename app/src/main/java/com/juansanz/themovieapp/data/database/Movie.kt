package com.juansanz.themovieapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Movie")
data class Movie(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int,
    val status: String,
    val releaseDate: String,
    val revenue: Long,
    val runtime: Int,
    val adult: String,
    val backdropPath: String,
    val budget: Long,
    val homepage: String,
    val imdbId: String,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val tagline: String,
    val genres: String,
    val productionCompanies: String,
    val productionCountries: String,
    val spokenLanguages: String,
    val keywords: String,
    val favorite: Boolean,
)
