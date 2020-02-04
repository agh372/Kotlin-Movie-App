package com.arjunhegde.kotlin_movie.utilities

import com.arjunhegde.kotlin_movie.model.Movie
import com.arjunhegde.kotlin_movie.model.MovieUI


val IMAGE_PATH = "https://image.tmdb.org/t/p/w500"

fun Movie.toUIMovie(): MovieUI {
    val imageValue = if(!image.isNullOrEmpty()) "$IMAGE_PATH$image" else ""

    return MovieUI(this.name, this.overview, this.first_air_date, this.original_language, imageValue, this.rating)
}