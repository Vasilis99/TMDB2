package com.vasilis.tmdb.views

import android.widget.TextView
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.vasilis.tmdb.MyItemDecoration
import com.vasilis.tmdb.views.shimmer.RecyclerViewShimmer


class TopRatedMoviesView(context: Context) : ConstraintLayout(context) {
    val title = TextView(context).apply {
        id = View.generateViewId()
        typeface = Typeface.DEFAULT_BOLD
        setTextColor(Color.BLACK)
        text = "Top rated Movies"
        textSize = 20F
    }

    public val moviesRecyclerView = RecyclerView(context).apply { id = View.generateViewId() }
    public val shimmer = RecyclerViewShimmer(context).apply { id = View.generateViewId() }

    init {

        apply { id = View.generateViewId() }
        setBackgroundColor(Color.WHITE)
        layoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT)
        title.layoutParams =
            LayoutParams(MATCH_PARENT, WRAP_CONTENT).apply {
                topToTop = this@TopRatedMoviesView.id
                startToStart = this@TopRatedMoviesView.id
                endToEnd = this@TopRatedMoviesView.id
                setMargins(20, 0, 20, 0)
            }
        addView(title)
        moviesRecyclerView.layoutParams =
            LayoutParams(MATCH_PARENT, 0).apply {
                topToBottom = title.id
                startToStart = this@TopRatedMoviesView.id
                endToEnd = this@TopRatedMoviesView.id
                bottomToBottom = this@TopRatedMoviesView.id

            }
        moviesRecyclerView.addItemDecoration(MyItemDecoration(20, 20, 20, 0))

        shimmer.layoutParams =
            LayoutParams(MATCH_PARENT, 0).apply {
                topToBottom = title.id
                startToStart = this@TopRatedMoviesView.id
                endToEnd = this@TopRatedMoviesView.id
                bottomToBottom = this@TopRatedMoviesView.id
            }
        moviesRecyclerView.visibility = INVISIBLE
        addView(moviesRecyclerView)
        addView(shimmer)

    }
}
