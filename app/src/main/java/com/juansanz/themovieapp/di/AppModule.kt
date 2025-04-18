package com.juansanz.themovieapp.di

import android.app.Application
import androidx.room.Room
import com.juansanz.data.MoviesRepository
import com.juansanz.data.datasource.MovieLocalDataSource
import com.juansanz.data.datasource.MovieRemoteDataSource
import com.juansanz.themovieapp.TheMovieApp
import com.juansanz.themovieapp.data.database.MovieDatabase
import com.juansanz.themovieapp.data.database.MovieRoomDataSource
import com.juansanz.themovieapp.data.server.MovieServerDataSource
import com.juansanz.themovieapp.data.server.RemoteService
import kotlinx.coroutines.DelicateCoroutinesApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@OptIn(DelicateCoroutinesApi::class)
object AppModule {
    const val APIURL: String = "http://192.168.0.8:8080/"

    fun provideOkHttpClient(): OkHttpClient =
        HttpLoggingInterceptor().run {
            level = HttpLoggingInterceptor.Level.BODY
            OkHttpClient.Builder().addInterceptor(this).build()
        }

    fun provideRemoteService(
        apiUrl: String = APIURL,
        okHttpClient: OkHttpClient = provideOkHttpClient(),
    ): RemoteService =
        Retrofit
            .Builder()
            .baseUrl(apiUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()

    fun provideDatabase(app: Application) =
        Room
            .databaseBuilder(
                app,
                MovieDatabase::class.java,
                "movie-db",
            ).allowMainThreadQueries() // Solo para Testes
            .build()

    fun provideMovieDao(db: MovieDatabase) = db.movieDao()

    fun providesMovieServerDataSource(remoteService: RemoteService = provideRemoteService()): MovieServerDataSource = MovieServerDataSource(remoteService)

    fun providesMovieLocalDataSource(): MovieLocalDataSource = MovieRoomDataSource(provideMovieDao(provideDatabase(app = TheMovieApp.instance)))

    fun providesMoviesRepository(
        remoteDataSource: MovieRemoteDataSource = providesMovieServerDataSource(),
        localDataSource: MovieLocalDataSource = providesMovieLocalDataSource(),
    ): MoviesRepository =
        MoviesRepository(
            remoteDataSource = remoteDataSource,
            localDataSource = localDataSource,
        )

    val moviesRepository: MoviesRepository by lazy {
        providesMoviesRepository()
    }
}
