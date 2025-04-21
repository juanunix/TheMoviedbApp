package com.juansanz.usecases

import com.juansanz.data.MoviesRepository
import com.juansanz.domain.Movie

class FindMovieByIdUseCase(
    private val moviesRepository: MoviesRepository,
) {
    suspend operator fun invoke(id: Int): Movie = moviesRepository.findById(id)
}
