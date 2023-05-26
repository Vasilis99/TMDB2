package com.vasilis.tmdb.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.vasilis.tmdb.FavoriteManager
import com.vasilis.tmdb.MainActivity
import com.vasilis.tmdb.adapters.FavoritesAdapter
import com.vasilis.tmdb.views.FavoritesView

class FavoritesFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("Favorites fragment")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FavoritesView(inflater.context).apply {
        shimmer.startShimmer()
        var saved = ViewModelProvider((activity as MainActivity))[FavoriteManager::class.java]
        tvShowsRecyclerView.layoutManager = LinearLayoutManager(context)
        tvShowsRecyclerView.adapter =
            FavoritesAdapter(1, saved, {
                saved.deleteTVShowFavorites(it)
                tvShowsRecyclerView.adapter?.notifyDataSetChanged()

            }) { tvShowID ->
                var tvShowFragment = TVShowFragment.newInstance(tvShowID)
                (activity as? MainActivity)?.myLayout?.id?.let { it1 ->
                    var transaction =
                        activity?.supportFragmentManager?.beginTransaction()
                    transaction?.replace(it1, tvShowFragment)?.commit()
                    transaction?.addToBackStack(null)
                }
            }

        var favObserver1 = Observer<List<Triple<Int, String, String>>> { updated ->
            println("Favorites observer 1 "+updated)
            var adapter = (tvShowsRecyclerView.adapter as FavoritesAdapter)
            //adapter.fav = updated
            adapter.notifyDataSetChanged()
        }
        saved.getTVShowsFavorites().observe(viewLifecycleOwner, favObserver1)



        moviesRecyclerView.layoutManager = LinearLayoutManager(context)
       // var temp: MutableList<TMDB.TVShowBasic> = mutableListOf()
        moviesRecyclerView.adapter =
            FavoritesAdapter(2, saved, {
                saved.deleteMovieFavorites(it)
                tvShowsRecyclerView.adapter?.notifyDataSetChanged()

            }) { movieID ->
                var moviesFragment = TVShowFragment.newInstance(movieID)
                (activity as? MainActivity)?.myLayout?.id?.let { it1 ->
                    var transaction =
                        activity?.supportFragmentManager?.beginTransaction()
                    transaction?.replace(it1, moviesFragment)?.commit()
                    transaction?.addToBackStack(null)
                }
            }

        var favObserver2 = Observer<List<Triple<Int, String, String>>> { updated ->
            println("Favorites observer 2"+updated)
            var adapter = (moviesRecyclerView.adapter as FavoritesAdapter)
           // adapter.fav = updated
            adapter.notifyDataSetChanged()
        }
        saved.getMoviesFavorites().observe(viewLifecycleOwner, favObserver2)

        shimmer.stopShimmer()
        this.visibility=View.VISIBLE
    }

}
