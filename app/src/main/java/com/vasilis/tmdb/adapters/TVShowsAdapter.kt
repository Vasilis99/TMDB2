package com.vasilis.tmdb.adapters

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.vasilis.tmdb.FavoriteManager
import com.vasilis.tmdb.TMDB
import com.vasilis.tmdb.views.RecyclerViewItemView
import com.vasilis.tmdb.R

class TVShowsAdapter(
    private var tvShows: MutableList<TMDB.TVShowBasic>,
    private var favorites: FavoriteManager,
    val clickListener: (Int) -> Unit,
    val add: (Triple<Int, String, String>) -> Unit,
    val del: (Triple<Int, String, String>) -> Unit,
    val context: Context
) : RecyclerView.Adapter<TVShowsAdapter.TVShowsViewHolder>() {
    var listTVShows: MutableList<TMDB.TVShowBasic> = tvShows
    var fav: List<Triple<Int, String, String>> = favorites.getTVShowsFavorites().value!!

    class TVShowsViewHolder(val view: RecyclerViewItemView) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVShowsViewHolder {
        return TVShowsViewHolder(RecyclerViewItemView(parent.context))
    }


    override fun getItemCount(): Int {
        return listTVShows.size
    }

    override fun onBindViewHolder(holder: TVShowsViewHolder, position: Int) {
        holder.view.apply {
            if (position % 2 == 0) {
                holder.view.setBackgroundResource(R.color.gray_1)
            } else {
                holder.view.setBackgroundResource(R.color.gray_2)
            }
        }



            holder.view.image.load("https://image.tmdb.org/t/p/w342" + listTVShows[position].poster_path) {
                placeholder(R.drawable.my_placeholder)
            }

        "${(position + 1)} ".also { holder.view.pos.text = it }
        holder.view.title.text = listTVShows[position].name
        holder.view.rating.text = listTVShows[position].vote_average.toString()
        holder.view.voteCount.text = "Votes: " + listTVShows[position].vote_count.toString()
        holder.view.overview.text = listTVShows[position].overview
        holder.view.favorite.isChecked = fav.find { it.first == listTVShows[position].id } != null


        val checkBox = holder.view.favorite
        checkBox.setOnClickListener {
            val tvShow =
                Triple(
                    listTVShows[position].id,
                    listTVShows[position].name,
                    listTVShows[position].poster_path
                )
            if (checkBox.isChecked) {
                add(tvShow)
            } else {

                del(tvShow)
            }
        }
        holder.itemView.setOnClickListener {
            clickListener(listTVShows[position].id)
            println("Title pressed: " + (listTVShows[position].name))
        }

        println(holder.view.image.height.toString() + "\n" + holder.view.image.width)
    }

}