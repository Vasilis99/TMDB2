package com.vasilis.tmdb.views

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.vasilis.tmdb.MyItemDecoration
import com.vasilis.tmdb.R
import com.vasilis.tmdb.views.shimmer.FavoritesShimmer


class FavoritesView(context: Context) : ScrollView(context) {
    var conLay = ConstraintLayout(context)
    var tvShowsCon = ConstraintLayout(context).apply {
        id = View.generateViewId()
    }
    var tvShowsTitle = TextView(context).apply {
        id = View.generateViewId()
        text = "Favorite TV Shows"
        setTextColor(Color.BLACK)
        textSize = 29F
    }
    var tvShowsArrow = ImageView(context).apply {
        id = View.generateViewId()
        setImageResource(R.drawable.arrow_drop_down)
    }

    var tvShowsRecyclerView = RecyclerView(context).apply {
        id = View.generateViewId()
    }
    var moviesCon = ConstraintLayout(context).apply {
        id = View.generateViewId()
    }
    var moviesTitle = TextView(context).apply {
        id = View.generateViewId()
        text = "Favorite Movies"
        setTextColor(Color.BLACK)
        textSize = 29F
    }
    var moviesRecyclerView = RecyclerView(context).apply {
        id = View.generateViewId()
    }
    var moviesArrow = ImageView(context).apply {
        id = View.generateViewId()
        setImageResource(R.drawable.arrow_drop_down)
    }
    var shimmer = FavoritesShimmer(context)

    init {
        layoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT)
        conLay.layoutParams = LayoutParams(MATCH_PARENT, 500)
        tvShowsCon.layoutParams = ConstraintLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT).apply {
            topToTop = conLay.id
            startToStart = conLay.id
            endToEnd = conLay.id
            setMargins(20, 10, 20, 0)
        }
        conLay.addView(tvShowsCon)
        tvShowsTitle.layoutParams = ConstraintLayout.LayoutParams(0, WRAP_CONTENT).apply {
            topToTop = tvShowsCon.id
            startToStart = tvShowsCon.id
            endToStart = tvShowsArrow.id
        }
        tvShowsCon.addView(tvShowsTitle)
        tvShowsCon.setBackgroundResource(R.color.gray_1)
        tvShowsArrow.layoutParams =
            ConstraintLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT).apply {
                topToTop = tvShowsCon.id
                bottomToBottom = tvShowsCon.id
                endToEnd = tvShowsCon.id
            }
        tvShowsCon.addView(tvShowsArrow)

        tvShowsRecyclerView.apply {
            layoutParams = ConstraintLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT).apply {
                topToBottom = tvShowsCon.id
                startToStart = conLay.id
                setMargins(20, 0, 20, 0)
            }
        }
        tvShowsRecyclerView.addItemDecoration(MyItemDecoration(0, 0, 10, 0))

        conLay.addView(tvShowsRecyclerView)
        tvShowsRecyclerView.visibility = GONE


        moviesCon.layoutParams = ConstraintLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT).apply {
            topToBottom = tvShowsRecyclerView.id
            startToStart = conLay.id
            endToEnd = conLay.id
            setMargins(20, 10, 20, 0)
        }
        conLay.addView(moviesCon)
        moviesTitle.layoutParams = ConstraintLayout.LayoutParams(0, WRAP_CONTENT).apply {
            topToTop = moviesCon.id
            startToStart = moviesCon.id
            endToStart = moviesArrow.id
        }
        moviesCon.setBackgroundResource(R.color.gray_1)
        moviesCon.addView(moviesTitle)
        moviesArrow.layoutParams = ConstraintLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT).apply {
            topToTop = moviesCon.id
            bottomToBottom = moviesCon.id
            endToEnd = moviesCon.id
        }

        moviesCon.addView(moviesArrow)

        moviesRecyclerView.apply {
            layoutParams = ConstraintLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT).apply {
                topToBottom = moviesCon.id
                startToStart = conLay.id
                endToEnd = conLay.id
                bottomToBottom = conLay.id
                setMargins(20, 0, 20, 0)
            }
        }
        moviesRecyclerView.addItemDecoration(MyItemDecoration(0, 0, 10, 0))

        conLay.addView(moviesRecyclerView)
        addView(conLay)
        moviesRecyclerView.visibility = GONE

        visibility = INVISIBLE
        conLay.addView(shimmer)
    }
}
