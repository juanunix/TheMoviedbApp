package com.juansanz.usecases

import com.juansanz.data.MoviesRepository
import com.juansanz.domain.Movie
import javax.inject.Inject

class SwitchMovieFavoriteUseCase
    @Inject
    constructor(
        private val repository: MoviesRepository,
    ) {
        suspend operator fun invoke(movie: Movie) = repository.switchFavorite(movie)
    }
