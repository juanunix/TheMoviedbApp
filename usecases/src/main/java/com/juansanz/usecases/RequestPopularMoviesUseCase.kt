package com.juansanz.usecases

import com.juansanz.data.MoviesRepository
import javax.inject.Inject

class RequestPopularMoviesUseCase
    @Inject
    constructor(
        private val moviesRepository: MoviesRepository,
    ) {
        suspend operator fun invoke(): Error? = moviesRepository.requestPopularMovies()
    }
