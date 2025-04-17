package com.juansanz.usecases

import com.juansanz.data.MoviesRepository
import javax.inject.Inject

class GetPopularMoviesUseCase
    @Inject
    constructor(
        private val repository: MoviesRepository,
    ) {
        operator fun invoke() = repository.popularMovies
    }
