package com.juansanz.usecases

import com.juansanz.data.MoviesRepository
import com.juansanz.domain.Movie

class GetAllMoviesUseCase(
    private val moviesRepository: MoviesRepository,
) {
    suspend fun invoke(): List<Movie> = moviesRepository.getAllMovies()
}
