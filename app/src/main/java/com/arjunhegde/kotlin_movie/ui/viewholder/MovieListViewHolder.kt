package com.arjunhegde.kotlin_movie.ui.viewholder

import android.content.res.Resources
import android.view.View
import androidx.core.content.res.TypedArrayUtils.getString
import androidx.recyclerview.widget.RecyclerView
import com.arjunhegde.kotlin_movie.R
import com.arjunhegde.kotlin_movie.model.Movie
import com.arjunhegde.kotlin_movie.model.MovieUI
import com.arjunhegde.kotlin_movie.ui.adapter.OnMovieClickListener
import com.arjunhegde.kotlin_movie.utilities.loadImage
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieListViewHolder(
    override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer
{

    fun initialize(item: MovieUI, action:OnMovieClickListener){
       // carName.text = item.name
        //carDescription.text = item.description
        //carLogo.setImageResource(item.logo)

        itemView.setOnClickListener{
            action.OnMovieClick(item,adapterPosition)
        }

    }

    fun bind(movie: MovieUI, action:OnMovieClickListener) {
        containerView.movie_image.loadImage(movie.image)
        containerView.movie_name.text = movie.name

        if(movie.rating != null) {
            containerView.movie_rating.text = movie.rating.toString()
        } else {
            containerView.movie_rating.text = ""
        }

        containerView.overview.text = movie.overview;
        containerView.air_date.text = "Air date: " + movie.first_air_date;
        containerView.country.text = "Language: " +movie.original_language;


        itemView.setOnClickListener{
            action.OnMovieClick(movie,adapterPosition)
        }

    }
}