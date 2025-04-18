package com.juansanz.usecases

import com.juansanz.data.MoviesRepository
import com.juansanz.domain.Movie

const val THRESHOLD = 1000.0

class RequestPopularMoviesUseCase(
    private val moviesRepository: MoviesRepository,
) {
    suspend operator fun invoke(): List<Movie> = moviesRepository.getAllPopularMovies().filter { it.popularity > THRESHOLD }
}
