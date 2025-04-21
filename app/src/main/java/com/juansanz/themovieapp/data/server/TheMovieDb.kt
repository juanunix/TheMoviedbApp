package com.juansanz.themovieapp.data.server

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TheMovieDb {
    private val okHttpClient = HttpLoggingInterceptor().run {
        level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder().addInterceptor(this).build()
    }

    val service: RemoteService = Retrofit
        .Builder()
        .baseUrl("http://192.168.0.8:8080/")
        .client(okHttpClient)
        // .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .run {
            create<RemoteService>(RemoteService::class.java)
        }
}
