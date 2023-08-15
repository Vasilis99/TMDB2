package com.vasilis.tmdb

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.vasilis.tmdb.favorites.FavoritesFragment
import com.vasilis.tmdb.search.SearchMovieTVShowFragment
import com.vasilis.tmdb.top_rated_movies.TopRatedMoviesFragment
import com.vasilis.tmdb.top_rated_tv_shows.TopRatedTVShowsFragment

class VPAdapter(fragmentActivity: Fragment) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment = when (position) {
            0 -> TopRatedTVShowsFragment()
            1 -> TopRatedMoviesFragment()
            2 -> SearchMovieTVShowFragment()
            else -> FavoritesFragment()
        }
        return fragment
    }
}
