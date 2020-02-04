package com.arjunhegde.kotlin_movie.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.arjunhegde.kotlin_movie.R
import com.arjunhegde.kotlin_movie.model.Movie
import com.arjunhegde.kotlin_movie.model.MovieUI
import com.arjunhegde.kotlin_movie.presenter.MainPresenter
import com.arjunhegde.kotlin_movie.ui.adapter.MovieListAdapter
import com.arjunhegde.kotlin_movie.ui.adapter.OnMovieClickListener
import com.arjunhegde.kotlin_movie.utilities.toast
import com.arjunhegde.kotlin_movie.view.MainView
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.scope.currentScope

class MainActivity : AppCompatActivity(), MainView, OnMovieClickListener {
    override fun OnMovieClick(item: MovieUI, position: Int) {
       // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private val presenter: MainPresenter by currentScope.inject()
    private lateinit var adapter: MovieListAdapter

    override fun onResume() {
        super.onResume()
        presenter.view = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.create()

        initializeRecyclerView()
    }

    private fun initializeRecyclerView() {
        adapter = MovieListAdapter({presenter.renderMoreMovies()},this)

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL ,false)
       // layoutManager.gapStrategy = LinearLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS

        movies_recyclerview.adapter = adapter
        movies_recyclerview.layoutManager = layoutManager

       movies_swiperefreshlayout.setOnRefreshListener { presenter.refresh() }
    }

    override fun listMovies(movies: List<MovieUI>) {
        adapter.addAll(movies)
    }

    override fun cleanMovies() {
        adapter.clean()
    }

    override fun hideLoading() {
        movies_swiperefreshlayout.isRefreshing = false
        movies_progressbar.visibility = View.GONE
    }

    override fun showLoading() {
        movies_progressbar.visibility = View.VISIBLE
    }

    override fun showToastMessage(stringId: Int) {
        this.toast(this, resources.getString(stringId))
    }

    override fun showErrorCase(stringId: Int) {
        movies_texterror.text = resources.getString(stringId)
        movies_texterror.visibility = View.VISIBLE
    }

    override fun hideErrorCase() {
        movies_texterror.visibility = View.GONE
    }
}
