package com.arjunhegde.kotlin_movie.data

import arrow.core.Either
import com.arjunhegde.kotlin_movie.model.Movie
import com.arjunhegde.kotlin_movie.utilities.Error


interface MovieRepository {
    suspend fun listMovies(page: Int): Either<Error, List<Movie>>
}