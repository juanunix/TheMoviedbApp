package com.juansanz.themovieapp.data.server

import retrofit2.http.GET
import retrofit2.http.Path

interface RemoteService {
    @GET("discover/movie?sort_by=popularity.desc")
    suspend fun listPopularMovies(
        /*  @Query("api_key") apiKey: String,
          @Query("region") region: String*/
    ): RemoteResult

    @GET("movies/searchByTitle/{title}")
    suspend fun searchByTitle(
        @Path("title") title: String,
    ): List<RemoteMovie>
}
