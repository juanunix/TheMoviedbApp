package com.juansanz.data.datasource

import com.juansanz.domain.Movie

interface RemoteDataSource {
    suspend fun findAllMovies(): List<Movie>
}
