package com.vasilis.tmdb.movie

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.vasilis.tmdb.R
import com.vasilis.tmdb.TMDB
import com.vasilis.tmdb.favorites.FavoriteManager
import com.vasilis.tmdb.views.MovieTVShowItem

class MoviesAdapter(var movies: List<TMDB.MovieBasic>, var favorites: FavoriteManager, val clickListener: (Int) -> Unit) :
    RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {
    var listMovies = movies

    class MoviesViewHolder(val view: MovieTVShowItem) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(MovieTVShowItem(parent.context))
    }

    override fun getItemCount(): Int {
        return listMovies.size
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.view.apply {
            if (position % 2 == 0) {
                holder.view.setBackgroundResource(R.color.gray_1)
            } else {
                holder.view.setBackgroundResource(R.color.gray_2)
            }
        }

        holder.view.image.load("https://image.tmdb.org/t/p/w342" + listMovies[position].poster_path) {
            placeholder(R.drawable.my_placeholder)
        }
        "${(position + 1)} ".also { holder.view.pos.text = it }
        holder.view.title.text = listMovies[position].title
        holder.view.rating.text = listMovies[position].vote_average.toString()
        holder.view.voteCount.text = "Votes: " + listMovies[position].vote_count.toString()
        holder.view.overview.text = listMovies[position].overview
        holder.view.favorite.isChecked = favorites.getMoviesFavorites().value!!.find { it.first == listMovies[position].id } != null
        val checkBox = holder.view.favorite
        checkBox.setOnClickListener {
            val movie = Triple(listMovies[position].id, listMovies[position].title, listMovies[position].poster_path)
            if (checkBox.isChecked) {
                favorites.addMovieFavorites(movie)
            } else {
                favorites.deleteMovieFavorites(movie)
            }
        }
        holder.itemView.setOnClickListener {
            clickListener(listMovies[position].id)
            println("Title pressed: " + (listMovies[position].title))
        }
    }
}
