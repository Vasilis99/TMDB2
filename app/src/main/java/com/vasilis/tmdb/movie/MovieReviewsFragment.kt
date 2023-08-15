package com.vasilis.tmdb.movie

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.vasilis.tmdb.MyApi
import com.vasilis.tmdb.RetrofitHelper
import com.vasilis.tmdb.TMDB
import com.vasilis.tmdb.views.ReviewsView

class MovieReviewsFragment : Fragment() {
    var movieID = 0
    var movieTitle = ""
    lateinit var movieReviews: TMDB.Reviews

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movieID = it.getInt("movieID")
            movieTitle = it.getString("movieTitle")!!

            println("On create $movieID $movieTitle")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = ReviewsView(inflater.context).apply {
        val myApi = RetrofitHelper.getInstance().create(MyApi::class.java)
        lifecycleScope.launchWhenResumed {
            val response = myApi.getMovieReviews(movieID)
            movieReviews = response.body()!!
            title.text = "$movieTitle reviews"

            var reviewView: ReviewsView.ReviewView
            for ((i, x) in movieReviews.results.withIndex()) {
                reviewView = ReviewsView.ReviewView(context)
                reviewView.apply {
                    layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
                }
                reviewView.author.title.text = "Author"
                reviewView.author.desc.text = x.author
                reviewView.content.title.text = "Content"
                reviewView.content.desc.text = x.content
                reviewView.createdAt.title.text = "Created at"
                reviewView.createdAt.desc.text = x.created_at
                reviewView.updatedAt.title.text = "Updated at"
                reviewView.updatedAt.desc.text = x.updated_at
                reviewView.url.title.text = "URL"
                reviewView.url.desc.text = x.url

                linLayout.addView(reviewView)
            }

            if (movieReviews.results.isEmpty()) {
                var noReviews = TextView(context)
                noReviews.apply {
                    setTextColor(Color.BLACK)
                    text = "No reviews"
                }
                linLayout.addView(noReviews)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(id: Int, title: String) =
            MovieReviewsFragment().apply {
                arguments = Bundle().apply {
                    putInt("movieID", id)
                    putString("movieTitle", title)
                }
            }
    }
}
