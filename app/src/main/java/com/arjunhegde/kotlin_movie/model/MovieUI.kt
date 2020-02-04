package com.arjunhegde.kotlin_movie.model

data class MovieUI(
    val name: String,
    val overview: String,
    val first_air_date: String,
    val original_language: String,
    val image: String,
    val rating: Float?
)
{
    override fun toString(): String = "UIMovie {Name: $name, Image: $image, rating: $rating}"
}