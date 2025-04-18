package com.juansanz.themovieapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juansanz.data.MoviesRepository
import com.juansanz.domain.Error
import com.juansanz.domain.Movie
import com.juansanz.themovieapp.data.toError
import com.juansanz.themovieapp.di.AppModule
import com.juansanz.usecases.FindMovieByIdUseCase
import com.juansanz.usecases.RequestPopularMoviesUseCase
import com.juansanz.usecases.ToggleMovieFavoriteUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel(
    private val movieId: Int,
    val moviesRepository: MoviesRepository = AppModule.moviesRepository,
    val findMovieByIdUseCase: FindMovieByIdUseCase = FindMovieByIdUseCase(moviesRepository),
    val requestPopularMoviesUse: RequestPopularMoviesUseCase = RequestPopularMoviesUseCase(moviesRepository),
    private val toggleMovieFavoriteUseCase: ToggleMovieFavoriteUseCase = ToggleMovieFavoriteUseCase(moviesRepository),
) : ViewModel() {
    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        // Cargar la película por ID
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val movie = findMovieByIdUseCase(movieId)
                _state.update { UiState(movie = movie) }
            } catch (cause: Throwable) {
                _state.update { it.copy(error = cause.toError()) }
            }
        }

        // TODO
        // Cargar películas populares
        /*viewModelScope.launch(Dispatchers.IO) {
            try {
                val popularMovies = requestPopularMoviesUse()
                _state.update { UiState(popularMovies = popularMovies) }
            } catch (cause: Throwable) {
                _state.update { it.copy(error = cause.toError()) }
            }
        }*/
    }

    fun onFavoriteClicked() {
        viewModelScope.launch {
            _state.value.movie?.let { movie ->
                val result = toggleMovieFavoriteUseCase.invoke(movie = movie)
                _state.update { it.copy(movie = result) }
            }
        }
    }

    data class UiState(
        val movie: Movie? = null,
        val popularMovies: List<Movie>? = null,
        val error: Error? = null,
    )
}
