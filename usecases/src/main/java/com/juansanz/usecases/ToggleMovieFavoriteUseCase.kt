package com.juansanz.usecases

import com.juansanz.data.MoviesRepository
import com.juansanz.domain.Movie

class ToggleMovieFavoriteUseCase(
    private val moviesRepository: MoviesRepository,
) {
    suspend fun invoke(movie: Movie): Movie =
        with(movie) {
            copy(favorite = !favorite).also { moviesRepository.update(it) }
        }
}
