package com.juansanz.themovieapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juansanz.themovieapp.ui.screens.model.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {
    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            /*findMovieUseCase(movieId)
                .catch { cause -> _state.update { it.copy(error = cause.toError()) } }
                .collect { movie -> _state.update { UiState(movie = movie) } }*/
        }
    }

    fun onFavoriteClicked() {
        viewModelScope.launch {
            /*_state.value.movie?.let { movie ->
                val error = switchMovieFavoriteUseCase(movie)
                _state.update { it.copy(error = error) }
            }*/
        }
    }

    data class UiState(
        val movie: Movie? = null,
        val error: Error? = null,
    )
}
