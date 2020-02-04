package com.arjunhegde.kotlin_movie.model

data class ResponseMovieDB(
    val page: Int?,
    val total_results: Int?,
    val total_pages: Int?,
    val results: List<MovieDB>?
)