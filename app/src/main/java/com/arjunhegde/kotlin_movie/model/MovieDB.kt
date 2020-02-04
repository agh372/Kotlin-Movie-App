package com.arjunhegde.kotlin_movie.model

data class MovieDB (
    val id: Int?,
    val name: String?,
    val overview: String?,
    val first_air_date: String?,
    val original_language: String?,
    val poster_path: String?,
    val vote_average: Float?
)