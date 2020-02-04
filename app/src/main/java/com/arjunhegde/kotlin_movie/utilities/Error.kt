package com.arjunhegde.kotlin_movie.utilities

sealed class Error: Exception() {
    object NoInternetConnectionException : Error()
    object NoMoreMoviesException : Error()
    object UnknownException: Error()
}