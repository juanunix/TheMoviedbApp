package com.juansanz.usecases

import com.juansanz.data.MoviesRepository

class GetPopularMoviesUseCase(
    private val repository: MoviesRepository,
) {
    operator fun invoke() = repository.popularMovies
}
