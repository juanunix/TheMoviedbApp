package com.juansanz.data.datasource

import com.juansanz.domain.Error
import com.juansanz.domain.Movie

interface MovieLocalDataSource {
    val movies: List<Movie>

    suspend fun isEmpty(): Boolean

    fun findById(id: Int): Movie

    suspend fun save(movies: List<Movie>): Error?
}
