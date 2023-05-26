package com.vasilis.tmdb.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.vasilis.tmdb.FavoriteManager
import com.vasilis.tmdb.R
import com.vasilis.tmdb.views.FavoritesRecyclerViewItem

class FavoritesAdapter(
    var type: Int,
    var favorites: FavoriteManager,
    val binClickListener: (Triple<Int,String,String>) -> Unit,
    val itemClickListener: (Int) -> Unit

) : RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>() {

    class FavoritesViewHolder(val view: FavoritesRecyclerViewItem) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {

        return FavoritesViewHolder(FavoritesRecyclerViewItem(parent.context))
    }


    override fun getItemCount(): Int {
        var size = 0
        size = if (type == 1) {
           favorites.getTVShowsFavorites().value!!.size
        } else {
            favorites.getMoviesFavorites().value!!.size
        }
        return size
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {

        holder.view.apply {
            if (position % 2 == 0) {
                holder.view.setBackgroundResource(R.color.gray_1)
            } else {
                holder.view.setBackgroundResource(R.color.gray_2)
            }
        }
        "${(position + 1)} ".also { holder.view.pos.text = it }
        if(type==1){
            var tvShow = favorites.getTVShowsFavorites().value!![position]
            holder.view.name.text=tvShow.second
            holder.view.image.load("https://image.tmdb.org/t/p/w154" + tvShow.third)
            holder.itemView.setOnClickListener {
                itemClickListener(tvShow.first)
            }
            holder.view.delete.setOnClickListener {
                binClickListener(tvShow)
            }
        }
        else{
            var movie = favorites.getMoviesFavorites().value!![position]
            holder.view.name.text=movie.second
            holder.view.image.load("https://image.tmdb.org/t/p/w154" + movie.third)
            holder.itemView.setOnClickListener {
                itemClickListener(movie.first)
            }
            holder.view.delete.setOnClickListener {
                binClickListener(movie)
            }
        }


    }
}