package com.arjunhegde.kotlin_movie.network

import arrow.core.Either
import com.arjunhegde.kotlin_movie.data.MovieRepository
import com.arjunhegde.kotlin_movie.model.Movie
import com.arjunhegde.kotlin_movie.utilities.Error

class ListMovies (
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(page: Int): Either<Error, List<Movie>> = movieRepository.listMovies(page)
}