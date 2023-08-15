package com.vasilis.tmdb.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.vasilis.tmdb.MainActivity
import com.vasilis.tmdb.R
import com.vasilis.tmdb.tv_show.TVShowFragment

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
        val saved = ViewModelProvider((activity as MainActivity))[FavoriteManager::class.java]
        tvShowsRecyclerView.layoutManager = LinearLayoutManager(context)
        tvShowsRecyclerView.adapter =
            FavoritesAdapter(1, saved, {
                saved.deleteTVShowFavorites(it)
                tvShowsRecyclerView.adapter?.notifyDataSetChanged()
            }) { tvShowID ->
                val tvShowFragment = TVShowFragment.newInstance(tvShowID)
                (activity as? MainActivity)?.myLayout?.id?.let { it1 ->
                    val transaction =
                        activity?.supportFragmentManager?.beginTransaction()
                    transaction?.replace(it1, tvShowFragment)?.commit()
                    transaction?.addToBackStack(null)
                }
            }

        val favObserver1 = Observer<List<Triple<Int, String, String>>> { updated ->
            println("Favorites observer 1 " + updated)
            val adapter = (tvShowsRecyclerView.adapter as FavoritesAdapter)
            // adapter.fav = updated
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
                val moviesFragment = TVShowFragment.newInstance(movieID)
                (activity as? MainActivity)?.myLayout?.id?.let { it1 ->
                    val transaction =
                        activity?.supportFragmentManager?.beginTransaction()
                    transaction?.replace(it1, moviesFragment)?.commit()
                    transaction?.addToBackStack(null)
                }
            }

        val favObserver2 = Observer<List<Triple<Int, String, String>>> { updated ->
            println("Favorites observer 2" + updated)
            val adapter = (moviesRecyclerView.adapter as FavoritesAdapter)
            // adapter.fav = updated
            adapter.notifyDataSetChanged()
        }
        saved.getMoviesFavorites().observe(viewLifecycleOwner, favObserver2)

        shimmer.stopShimmer()
        this.visibility = View.VISIBLE
        tvShowsArrow.setOnClickListener {
            if (tvShowsRecyclerView.visibility == View.VISIBLE) {
                // The transition of the hiddenView is carried out by the TransitionManager class.
                // Here we use an object of the AutoTransition Class to create a default transition
                // TransitionManager.beginDelayedTransition(tvShowsRecyclerView, AutoTransition())
                tvShowsRecyclerView.visibility = View.GONE
                tvShowsArrow.setImageResource(R.drawable.arrow_drop_down)
            } else {
                // TransitionManager.beginDelayedTransition(tvShowsRecyclerView, AutoTransition())
                tvShowsRecyclerView.visibility = View.VISIBLE
                tvShowsArrow.setImageResource(R.drawable.arrow_drop_up)
            }
        }
        moviesArrow.setOnClickListener {
            if (moviesRecyclerView.visibility == View.VISIBLE) {
                // The transition of the hiddenView is carried out by the TransitionManager class.
                // Here we use an object of the AutoTransition Class to create a default transition
                // TransitionManager.beginDelayedTransition(tvShowsRecyclerView, AutoTransition())
                moviesRecyclerView.visibility = View.GONE
                moviesArrow.setImageResource(R.drawable.arrow_drop_down)
            } else {
                // TransitionManager.beginDelayedTransition(tvShowsRecyclerView, AutoTransition())
                moviesRecyclerView.visibility = View.VISIBLE
                moviesArrow.setImageResource(R.drawable.arrow_drop_up)
            }
        }
    }
}
