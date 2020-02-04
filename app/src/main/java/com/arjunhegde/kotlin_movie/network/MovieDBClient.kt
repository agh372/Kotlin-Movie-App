package com.arjunhegde.kotlin_movie.network

import com.arjunhegde.kotlin_movie.model.ResponseMovieDB
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDBClient {
    @GET("/3/tv/popular")
    suspend fun getMoviesFromDB(
        @Query("api_key") apikey: String,
        @Query("page") page: Int
    ): ResponseMovieDB

}