package com.juansanz.usecases

import com.juansanz.data.MoviesRepository
import com.juansanz.domain.Movie
import kotlinx.coroutines.flow.Flow

class FindMovieUseCase(
    private val repository: MoviesRepository,
) {
    suspend operator fun invoke(id: String): Flow<Movie> = repository.findById(id)
}
