package com.juansanz.data.datasource

import com.juansanz.domain.Error
import com.juansanz.domain.Movie
import kotlinx.coroutines.flow.Flow

interface MovieLocalDataSource {
    val movies: Flow<List<Movie>>

    suspend fun isEmpty(): Boolean

    fun findById(id: Int): Flow<Movie>

    suspend fun save(movies: List<Movie>): Error?
}
