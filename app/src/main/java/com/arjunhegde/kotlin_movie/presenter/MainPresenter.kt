package com.arjunhegde.kotlin_movie.presenter

import arrow.core.Either
import com.arjunhegde.kotlin_movie.R
import com.arjunhegde.kotlin_movie.network.ListMovies
import com.arjunhegde.kotlin_movie.utilities.Error
import com.arjunhegde.kotlin_movie.utilities.toUIMovie
import kotlinx.coroutines.*
import com.arjunhegde.kotlin_movie.view.MainView


class MainPresenter(
    private val listMovies: ListMovies
): CoroutineScope by MainScope() {
    var view: MainView? = null
    var page: Int = 1

    fun create() {
        renderMovies()
    }

    fun refresh() {
        view?.cleanMovies()
        page = 1
        renderMovies()
    }

    private fun renderMovies() = launch {
        view?.showLoading()

        val resultMovies = withContext(Dispatchers.IO) { listMovies(page) }
        view?.hideLoading()

        when (resultMovies) {
            is Either.Right -> {
                view?.hideErrorCase()
                view?.listMovies(resultMovies.b.map { it.toUIMovie() })
            }
            is Either.Left -> {
                when (resultMovies.a) {
                    is Error.NoInternetConnectionException -> showErrorMessage(R.string.noInternetConectionError)
                    is Error.NoMoreMoviesException -> showErrorMessage(R.string.noMoreMovieError)
                    is Error.UnknownException -> showErrorMessage(R.string.emptyList)
                }
            }
        }

        Unit
    }

    private fun showErrorMessage(stringIdError: Int) {
        if (page == 1) {
            view?.showErrorCase(stringIdError)
        } else {
            view?.showToastMessage(stringIdError)
        }
    }

    fun renderMoreMovies() {
        page = page.plus(1)

        renderMovies()
    }

    fun onDestroy() {
        view = null
    }
}