package com.arjunhegde.kotlin_movie.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arjunhegde.kotlin_movie.R
import com.arjunhegde.kotlin_movie.model.Movie
import com.arjunhegde.kotlin_movie.model.MovieUI
import com.arjunhegde.kotlin_movie.ui.viewholder.MovieListViewHolder
import com.arjunhegde.kotlin_movie.utilities.inflate

class MovieListAdapter(
    val fetchNewPage: () -> Unit, var clickListner: OnMovieClickListener
) : RecyclerView.Adapter<MovieListViewHolder>() {
    private var distance: Int = 6
    private var waitingForNextPage: Boolean = false

    private var movies: MutableList<MovieUI> = ArrayList()

    fun addAll(collection: Collection<MovieUI>) {
        setWaitingForNextPageFalse()
        movies.addAll(collection)
        notifyDataSetChanged()
    }

    fun clean() {
        setWaitingForNextPageFalse()
        movies.clear()
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        if (!waitingForNextPage) {
            if (position.plus(distance) >= itemCount) {
                setWaitingForNextPageTrue()
                fetchNewPage()
            }
        }

        return super.getItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder =
        MovieListViewHolder(parent.inflate(R.layout.movie_item))

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int){
        holder.bind(movies.get(position),clickListner)
    }

    override fun getItemCount(): Int = movies.size

    private fun setWaitingForNextPageFalse() {
        waitingForNextPage = false
    }

    private fun setWaitingForNextPageTrue() {
        waitingForNextPage = true
    }
}

interface OnMovieClickListener{
    fun OnMovieClick(item: MovieUI, position: Int )
}
