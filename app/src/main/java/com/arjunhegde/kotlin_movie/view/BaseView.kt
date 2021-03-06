package com.arjunhegde.kotlin_movie.view

interface BaseView {
    fun hideLoading()
    fun showLoading()

    fun showToastMessage(stringId: Int)

    fun showErrorCase(stringId: Int)
    fun hideErrorCase()
}