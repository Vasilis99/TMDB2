package com.vasilis.tmdb.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.vasilis.tmdb.favorites.FavoriteManager
import com.vasilis.tmdb.MainActivity
import com.vasilis.tmdb.MyApi
import com.vasilis.tmdb.TMDB
import com.vasilis.tmdb.tv_show.TVShowFragment
import com.vasilis.tmdb.tv_show.TVShowsAdapter
import com.vasilis.tmdb.top_rated_tv_shows.TopRatedTVShowsView
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchedTVShowsFragment:Fragment() {
    private lateinit var tvShows: MutableList<TMDB.TVShowBasic>
    //lateinit var topRatedMoviesRecyclerView: RecyclerView
    var searchTVShow:String=""
    object RetrofitHelper {
        private const val baseUrl = "https://api.themoviedb.org/3/search/"
        fun getInstance(): Retrofit {
            return Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
            if (it != null) {
                searchTVShow = it.getString("tvShow")!!
            }
        }
        tvShows= mutableListOf()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.context.let {
        TopRatedTVShowsView(it).apply {
            val myApi = RetrofitHelper.getInstance().create(MyApi::class.java)
            lifecycleScope.launchWhenResumed {
                shimmer.startShimmer()
                var response= myApi.searchTVShow("287f6ab6616e3724955e2b4c6841ea63",searchTVShow)
                title.text= "Searched \"$searchTVShow\""
                var results = response.body()!!.results
                for (x in results!!) {
                    tvShows.add(x)
                }
                var favorites= ViewModelProvider((activity as MainActivity))[FavoriteManager::class.java]
                var f=favorites
                tvShowsRecyclerView.layoutManager =
                    LinearLayoutManager(context)
                tvShowsRecyclerView.adapter =
                    TVShowsAdapter(tvShows, favorites,{ tvShowID ->
                        for (x in tvShows) {
                            if (x.id == tvShowID) {
                                var tvShowFragment = TVShowFragment.newInstance(tvShowID)

                                (activity as? MainActivity)?.myLayout?.id?.let { it1 ->

                                    var transaction =
                                        activity?.supportFragmentManager?.beginTransaction()
                                    transaction?.add(it1, tvShowFragment)?.commit()
                                    transaction?.addToBackStack(null)
                                }
                                break
                            }
                        }
                    },{
                            tvShow->
                        f.addTVShowFavorites(tvShow)
                    },{ tvShow->
                        f.deleteTVShowFavorites(tvShow)
                    },context)


                var favObserver= Observer<List<Triple<Int, String, String>>> {updated->
                    var adapter=(tvShowsRecyclerView.adapter as TVShowsAdapter)
                    adapter.fav=updated
                    adapter.notifyDataSetChanged()
                }
                favorites.getTVShowsFavorites().observe(viewLifecycleOwner,favObserver)
                shimmer.stopShimmer()
                shimmer.visibility=View.INVISIBLE
                tvShowsRecyclerView.visibility=View.VISIBLE
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    companion object {
        @JvmStatic
        fun newInstance(tvShow: String) =
            SearchedTVShowsFragment().apply {
                arguments = Bundle().apply {
                    putString("tvShow",tvShow)
                }
            }
    }
}