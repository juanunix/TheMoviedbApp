package com.juansanz.themovieapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juansanz.domain.Error
import com.juansanz.domain.Movie
import com.juansanz.themovieapp.data.toError
import com.juansanz.themovieapp.di.AppModule
import com.juansanz.usecases.GetPopularMoviesUseCase
import com.juansanz.usecases.RequestPopularMoviesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel constructor(
    val appmodule: AppModule = AppModule,
    val getPopularMoviesUseCase: GetPopularMoviesUseCase = GetPopularMoviesUseCase(
        appmodule.moviesRepository,
    ),
    private val requestPopularMoviesUseCase: RequestPopularMoviesUseCase = RequestPopularMoviesUseCase(
        appmodule.moviesRepository,
    ),
) : ViewModel() {
    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
//        testDatabase()
        onUiReady()
        viewModelScope.launch {
            getPopularMoviesUseCase()
                .catch { cause -> _state.update { it.copy(error = cause.toError()) } }
                .collect { movies -> _state.update { UiState(movies = movies) } }
        }
    }

    fun onUiReady() {
        viewModelScope.launch {
            _state.value = _state.value.copy(loading = true)
            val error = requestPopularMoviesUseCase()
            _state.value = _state.value.copy(loading = false, error = error)
        }
    }

    fun testDatabase() {
        /*viewModelScope.launch(Dispatchers.IO) {
            val movieDB = provideMovieDao(provideDatabase(app = TheMovieApp.instance ))
            movieDB.insertMovies(
                listOf(
                    DBmovie(
                        id = 565770,
                        title = "Blue Beetle",
                        voteAverage = 7.139,
                        voteCount = 1023,
                        status = "Released",
                        releaseDate = "2023-08-16",
                        revenue = 124818235,
                        runtime = 128,
                        adult = "false",
                        backdropPath = "/1syW9SNna38rSl9fnXwc9fP7POW.jpg",
                        budget = 120000000,
                        homepage = "https://www.dc.com/bluebeetle",
                        imdbId = "tt9362930",
                        originalLanguage = "en",
                        originalTitle = "Blue Beetle",
                        overview = "Recent college grad Jaime Reyes returns home full of aspirations for his future, only to find that home is not quite as he left it. As he searches to find his purpose in the world, fate intervenes when Jaime unexpectedly finds himself in possession of an ancient relic of alien biotechnology: the Scarab.",
                        popularity = 2994.357,
                        posterPath = "/mXLOHHc1Zeuwsl4xYKjKh2280oL.jpg",
                        tagline = "Jaime Reyes is a superhero whether he likes it or not.",
                        genres = listOf("Action", "Science Fiction", "Adventure").toString(),
                        productionCompanies = listOf("Warner Bros. Pictures", "The Safran Company", "DC Films").toString(),
                        productionCountries = listOf("United States of America").toString(),
                        spokenLanguages = listOf("English", "Portuguese", "Spanish").toString(),
                        keywords = listOf(
                            "armor", "superhero", "family relationships", "family", "high tech",
                            "job hunting", "mexican american", "aftercreditsstinger", "duringcreditsstinger",
                            "immigrant family", "college graduate", "dc extended universe (dceu)",
                            "alien technology", "brother sister relationship", "latino",
                        ).toString(),
                        favorite = false,
                    ),
                ),
            )
        }*/
    }

    /*fun provideDatabase(app: Application) = Room.databaseBuilder(
        app,
        MovieDatabase::class.java,
        "movie-db",
    ).build()

    fun provideMovieDao(db: MovieDatabase) = db.movieDao()*/

    data class UiState(
        val loading: Boolean = false,
        val movies: List<Movie>? = null,
        val error: Error? = null,
    )
}
