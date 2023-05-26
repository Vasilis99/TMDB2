package com.vasilis.tmdb.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.vasilis.tmdb.FavoriteManager
import com.vasilis.tmdb.MainActivity
import com.vasilis.tmdb.adapters.MoviesAdapter
import com.vasilis.tmdb.MyApi
import com.vasilis.tmdb.TMDB
import com.vasilis.tmdb.views.TopRatedMoviesView
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchedMoviesFragment:Fragment() {
    lateinit var movies: MutableList<TMDB.MovieBasic>
    var searchMovie=""
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
        arguments?.let {
            searchMovie=it.getString("searchMovie")!!
        }
        movies = mutableListOf()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.context.let {
        TopRatedMoviesView(it).apply {
            val myApi = SearchedTVShowsFragment.RetrofitHelper.getInstance().create(MyApi::class.java)
            lifecycleScope.launchWhenResumed {
                shimmer.startShimmer()
                var response= myApi.searchMovie("287f6ab6616e3724955e2b4c6841ea63",searchMovie)
                title.text= "Searched \"$searchMovie\""
                var pages=response.body()!!.total_pages
                var result = response.body()!!.results
                for (x in result!!) {
                    movies.add(x)
                }

                var favorites= ViewModelProvider((activity as MainActivity))[FavoriteManager::class.java]
                moviesRecyclerView.layoutManager =
                    LinearLayoutManager(context)
                moviesRecyclerView.adapter =
                    MoviesAdapter(movies, favorites){ movieID ->
                        for (x in movies) {
                            if (x.id == movieID) {
                                println(movieID)
                                var moviesFragment = MovieFragment.newInstance(movieID)

                                (activity as? MainActivity)?.myLayout?.id?.let { it1 ->

                                    var transaction =
                                        activity?.supportFragmentManager?.beginTransaction()
                                    transaction?.add(it1, moviesFragment)?.commit()
                                    transaction?.addToBackStack(null)
                                }
                                break
                            }
                        }
                    }


                var favObserver= Observer<List<Triple<Int, String, String>>> {updated->

                    var adapter=(moviesRecyclerView.adapter as MoviesAdapter)
                    //adapter.fav=updated
                    adapter.notifyDataSetChanged()
                }
                favorites.getMoviesFavorites().observe(viewLifecycleOwner,favObserver)
                shimmer.stopShimmer()
                shimmer.visibility=View.INVISIBLE
                moviesRecyclerView.visibility=View.VISIBLE
            }
        }
    }
    companion object{
        @JvmStatic
        fun newInstance(searchMovie: String) =
            SearchedMoviesFragment().apply {
                arguments = Bundle().apply {
                    putString("searchMovie",searchMovie)
                }
            }
    }
}