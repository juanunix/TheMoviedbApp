package com.juansanz.themovieapp.di

import android.app.Application
import com.juansanz.data.MoviesRepository
import com.juansanz.data.RegionRepository
import com.juansanz.data.datasource.LocalDataSource
import com.juansanz.data.datasource.RemoteDataSource
import com.juansanz.themovieapp.data.database.MovieDatabase
import com.juansanz.themovieapp.data.database.RoomDataSource
import com.juansanz.themovieapp.data.server.MovieServerDataSource
import com.juansanz.themovieapp.ui.DetailViewModel
import com.juansanz.themovieapp.ui.MainViewModel
import com.juansanz.usecases.FindMovieByIdUseCase
import com.juansanz.usecases.GetAllMoviesUseCase
import com.juansanz.usecases.RequestPopularMoviesUseCase
import com.juansanz.usecases.ToggleMovieFavoriteUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun Application.initDI() {
    startKoin {
        androidLogger()
        androidContext(this@initDI)
        modules(listOf(appModule, dataModule, useCaseModule, viewModelModule))
    }
}

private val appModule = module {
    single { MovieDatabase.build(get()) }
    factory<LocalDataSource> { RoomDataSource(get()) }
    factory<RemoteDataSource> { MovieServerDataSource() }
    single<CoroutineDispatcher> { Dispatchers.Main }
}

val dataModule = module {
    factory { RegionRepository(get(), get()) }
    factory { MoviesRepository(get(), get()) }
}

private val useCaseModule = module {
    single { GetAllMoviesUseCase(get()) }
    single { FindMovieByIdUseCase(get()) }
    single { ToggleMovieFavoriteUseCase(get()) }
    single { RequestPopularMoviesUseCase(get()) }
}

private val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel {
        DetailViewModel(
            get(),
            get(),
            get(),
        )
    }
}
