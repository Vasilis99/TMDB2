package com.vasilis.tmdb.views

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.vasilis.tmdb.MyItemDecoration
import com.vasilis.tmdb.views.shimmer.FavoritesShimmer


class FavoritesView(context: Context) : ConstraintLayout(context) {
    var tvShowsTitle = TextView(context).apply {
        text = "Favorite TV Shows"
        setTextColor(Color.BLACK)
        textSize = 20F
        id = View.generateViewId()
    }
    var tvShowsRecyclerView = RecyclerView(context).apply { id = View.generateViewId() }
    var moviesTitle = TextView(context).apply {
        setText("Favorite Movies")
        setTextColor(Color.BLACK)
        textSize = 20F
        id = View.generateViewId()
    }
    var moviesRecyclerView = RecyclerView(context).apply { id = View.generateViewId() }
    var shimmer = FavoritesShimmer(context)

    init {
        apply { id = View.generateViewId() }
        tvShowsTitle.apply {
            layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT).apply {
                setMargins(20, 0, 20, 0)
                topToTop = this@FavoritesView.id
                startToStart = this@FavoritesView.id
                endToEnd = this@FavoritesView.id
            }
        }
        tvShowsRecyclerView.apply {
            layoutParams = LayoutParams(MATCH_PARENT, 800).apply {
                topToBottom = tvShowsTitle.id
                startToStart = this@FavoritesView.id
                endToEnd = this@FavoritesView.id
            }
        }
        tvShowsRecyclerView.addItemDecoration(MyItemDecoration(10, 10, 10, 0))
        moviesTitle.apply {
            layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT).apply {
                setMargins(20, 20, 20, 20)
                topToBottom = tvShowsRecyclerView.id
                startToStart = this@FavoritesView.id
                endToEnd = this@FavoritesView.id
            }
        }
        moviesRecyclerView.apply {
            layoutParams = LayoutParams(MATCH_PARENT, 800).apply {
                topToBottom = moviesTitle.id
                startToStart = this@FavoritesView.id
                endToEnd = this@FavoritesView.id
            }
        }
        moviesRecyclerView.addItemDecoration(MyItemDecoration(20, 20, 10, 0))

        addView(tvShowsTitle)
        addView(tvShowsRecyclerView)
        addView(moviesTitle)
        addView(moviesRecyclerView)
        visibility = INVISIBLE
        addView(shimmer)
    }
}
