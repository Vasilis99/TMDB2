package com.vasilis.tmdb.tv_show

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
import com.vasilis.tmdb.R
import com.vasilis.tmdb.RetrofitHelper
import com.vasilis.tmdb.TMDB
import com.vasilis.tmdb.views.ReviewsView

class TVShowReviewsFragment : Fragment() {
    var tvShowID = 0
    var tvShowTitle=""
    lateinit var tvShowReviews: TMDB.Reviews
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            tvShowID = it.getInt("tvShowID")
            tvShowTitle= it.getString("tvShowTitle")!!

        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = ReviewsView(inflater.context).apply {
        val myApi = RetrofitHelper.getInstance().create(MyApi::class.java)
        lifecycleScope.launchWhenResumed {
            val response = myApi.getTVShowReviews(tvShowID)

            tvShowReviews = response.body()!!
            title.text= "$tvShowTitle reviews"


            var reviewView: ReviewsView.ReviewView
            for((i,x) in tvShowReviews.results.withIndex()){
                reviewView= ReviewsView.ReviewView(context)
                reviewView.apply {
                    layoutParams= LinearLayout.LayoutParams(MATCH_PARENT,WRAP_CONTENT)
                }
                reviewView.author.title.text="Author"
                reviewView.author.desc.text=x.author
                reviewView.content.title.text="Content"
                reviewView.content.desc.text=x.content
                reviewView.createdAt.title.text="Created at"
                reviewView.createdAt.desc.text=x.created_at
                reviewView.updatedAt.title.text="Updated at"
                reviewView.updatedAt.desc.text=x.updated_at
                reviewView.url.title.text="URL"
                reviewView.url.desc.text=x.url
                val params = LinearLayout.LayoutParams(
                    MATCH_PARENT, WRAP_CONTENT
                )
                params.setMargins(0, 20, 0, 0) // LEFT, TOP, RIGHT, BOTTOM

                if(i%2==0){
                    reviewView.setBackgroundResource(R.color.gray_1)
                }
                else{
                    reviewView.setBackgroundResource(R.color.gray_2)
                }
                linLayout.addView(reviewView,params)

            }
            if(tvShowReviews.results.isEmpty()){
                var noReviews=TextView(context)
                noReviews.apply{
                    setTextColor(Color.BLACK)
                    text="No reviews"
                }
                linLayout.addView(noReviews)
            }


        }
    }

    companion object {
        @JvmStatic
        fun newInstance(id: Int,title:String) =
            TVShowReviewsFragment().apply {
                arguments = Bundle().apply {
                    putInt("tvShowID", id)
                    putString("tvShowTitle",title)
                }
            }
    }
}