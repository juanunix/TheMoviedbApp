package com.juansanz.usecases

import com.juansanz.data.MoviesRepository
import com.juansanz.domain.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FindMovieUseCase
    @Inject
    constructor(
        private val repository: MoviesRepository,
    ) {
        operator fun invoke(id: Int): Flow<Movie> = repository.findById(id)
    }
