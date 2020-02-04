package com.arjunhegde.kotlin_movie.view

import com.arjunhegde.kotlin_movie.model.MovieUI


interface MainView : BaseView {
    fun listMovies(movies: List<MovieUI>)
    fun cleanMovies()
}