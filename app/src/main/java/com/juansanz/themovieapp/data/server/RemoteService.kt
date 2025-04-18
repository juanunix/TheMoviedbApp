package com.juansanz.themovieapp.data.server

import retrofit2.http.GET
import retrofit2.http.Path

interface RemoteService {
    @GET("movies")
    suspend fun findAllMovies(): List<RemoteMovie>

    @GET("movies/searchByTitle/{title}")
    suspend fun searchByTitle(
        @Path("title") title: String,
    ): List<RemoteMovie>

    @GET("movies/{id}")
    suspend fun searchByID(
        @Path("id") id: String,
    ): RemoteMovie
}
