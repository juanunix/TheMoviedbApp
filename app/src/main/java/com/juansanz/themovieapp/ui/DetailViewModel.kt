package com.juansanz.themovieapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juansanz.domain.Error
import com.juansanz.domain.Movie
import com.juansanz.themovieapp.data.toError
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
    val findMovieByIdUseCase: FindMovieByIdUseCase,
    val requestPopularMovies: RequestPopularMoviesUseCase,
    private val toggleMovieFavoriteUseCase: ToggleMovieFavoriteUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        requestPopularMovies()
    }

    fun findMovieByIdUseCase(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val movie = findMovieByIdUseCase.invoke(movieId)
                _state.update { it.copy(movie = movie) }
            } catch (cause: Throwable) {
                _state.update { it.copy(error = cause.toError()) }
            }
        }
    }

    fun requestPopularMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val movies = requestPopularMovies.invoke()
                _state.update { it.copy(popularMovies = movies) }
            } catch (cause: Throwable) {
                _state.update { it.copy(error = cause.toError()) }
            }
        }
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
