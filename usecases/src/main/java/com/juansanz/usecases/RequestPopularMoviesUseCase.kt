package com.juansanz.usecases

import com.juansanz.data.MoviesRepository
import com.juansanz.domain.Error

class RequestPopularMoviesUseCase(
    private val moviesRepository: MoviesRepository,
) {
    suspend operator fun invoke(): Error? = moviesRepository.requestPopularMovies()
}
