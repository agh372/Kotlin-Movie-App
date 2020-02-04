package com.arjunhegde.kotlin_movie.data

import arrow.core.Either
import com.arjunhegde.kotlin_movie.model.Movie
import com.arjunhegde.kotlin_movie.network.MovieDBClientService
import com.arjunhegde.kotlin_movie.utilities.Error

class MovieRepositoryImpl(
    private val movieDBClientService: MovieDBClientService
) : MovieRepository {
    override suspend fun listMovies(page: Int): Either<Error, List<Movie>> {
        try {
            val listMoviesDB = movieDBClientService.getMoviesFromDB(page)

            if (listMoviesDB.isEmpty()) return Either.left(Error.NoMoreMoviesException)

            return Either.right(listMoviesDB.map { it.toMovie() })
        } catch (noInternetConnectionException: Error) {
            return Either.left(Error.NoInternetConnectionException)
        } catch (exception : Exception) {
            return Either.left(Error.UnknownException)
        }
    }
}