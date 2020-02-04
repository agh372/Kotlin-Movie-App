package com.arjunhegde.kotlin_movie.data

import com.arjunhegde.kotlin_movie.model.Movie
import com.arjunhegde.kotlin_movie.model.MovieDB

fun MovieDB.toMovie(): Movie
        = Movie(this.id?: 0, this.name?: "", this.overview?: "",this.first_air_date?: "",this.original_language?: "",this.poster_path, this.vote_average)