package com.vasilis.tmdb.top_rated_tv_shows

import com.vasilis.tmdb.views.shimmer.RecyclerViewShimmer
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.vasilis.tmdb.MyItemDecoration


class TopRatedTVShowsView(context: Context) : ConstraintLayout(context) {
    val title = TextView(context).apply {
        id=View.generateViewId()
        typeface= Typeface.DEFAULT_BOLD
        setTextColor(Color.BLACK)
        textSize=22F
    }

    val tvShowsRecyclerView = RecyclerView(context).apply{id=View.generateViewId()}
    val shimmer= RecyclerViewShimmer(context).apply{id=View.generateViewId()}


    init {

        apply{id=View.generateViewId()}
        setBackgroundColor(Color.WHITE)
        layoutParams= LayoutParams(MATCH_PARENT, MATCH_PARENT)
        title.layoutParams=LayoutParams(MATCH_PARENT, WRAP_CONTENT).apply {
            topToTop=this@TopRatedTVShowsView.id
            startToStart=this@TopRatedTVShowsView.id
            endToEnd=this@TopRatedTVShowsView.id
            setMargins(20,0,20,0)
        }
        addView(title)
        tvShowsRecyclerView.layoutParams =
            LayoutParams(MATCH_PARENT, 0).apply {
                topToBottom=title.id
                startToStart=this@TopRatedTVShowsView.id
                endToEnd=this@TopRatedTVShowsView.id
                bottomToBottom=this@TopRatedTVShowsView.id
            }
        tvShowsRecyclerView.addItemDecoration(MyItemDecoration(20,20,20,0))

        addView(tvShowsRecyclerView)

        shimmer.layoutParams =
            LayoutParams(MATCH_PARENT, 0).apply {
                topToBottom=title.id
                startToStart=this@TopRatedTVShowsView.id
                endToEnd=this@TopRatedTVShowsView.id
                bottomToBottom=this@TopRatedTVShowsView.id
            }
        tvShowsRecyclerView.visibility= INVISIBLE
        addView(shimmer)

    }

}