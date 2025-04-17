package com.juansanz.themovieapp.di

import com.juansanz.data.MoviesRepository
import com.juansanz.data.datasource.MovieRemoteDataSource
import com.juansanz.themovieapp.data.server.MovieServerDataSource
import com.juansanz.themovieapp.data.server.RemoteService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

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

    fun providesMovieServerDataSource(remoteService: RemoteService = provideRemoteService()): MovieServerDataSource = MovieServerDataSource(remoteService)

    fun providesMoviesRepository(remoteDataSource: MovieRemoteDataSource = providesMovieServerDataSource()): MoviesRepository = MoviesRepository(remoteDataSource)

    val moviesRepository: MoviesRepository by lazy {
        providesMoviesRepository()
    }
}

/*
abstract class AppDataModule {
    abstract fun bindLocalDataSource(localDataSource: MovieRoomDataSource): MovieLocalDataSource
    abstract fun bindRemoteDataSource(remoteDataSource: MovieServerDataSource): MovieRemoteDataSource
    abstract fun bindLocationDataSource(locationDataSource: PlayServicesLocationDataSource): LocationDataSource
    abstract fun bindPermissionChecker(permissionChecker: AndroidPermissionChecker): PermissionChecker
}
*/
