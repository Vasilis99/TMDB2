package com.vasilis.tmdb.views.shimmer

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.vasilis.tmdb.MyItemDecoration
import com.facebook.shimmer.ShimmerFrameLayout

class FavoritesShimmer(context: Context) : ShimmerFrameLayout(context) {
    var tvShowsTitle = TextView(context).apply {
        text = "Favorite TV Shows"
        setTextColor(Color.BLACK)
        textSize = 20F
        id = View.generateViewId()
    }
    var tvShowsRecyclerView = RecyclerView(context).apply { id = View.generateViewId() }
    var moviesTitle = TextView(context).apply {
        id = View.generateViewId()
        text = "Favorite Movies"
        setTextColor(Color.BLACK)
        textSize = 20F
    }
    var moviesRecyclerView = RecyclerView(context).apply { id = View.generateViewId() }
    var conLayout = ConstraintLayout(context).apply { id = View.generateViewId() }

    init {
        apply { id = View.generateViewId() }
        tvShowsTitle.apply {
            layoutParams = ConstraintLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(20, 0, 20, 0)
                topToTop = conLayout.id
                startToStart = conLayout.id
                endToEnd = conLayout.id
                setBackgroundColor(Color.GRAY)
            }
        }
        tvShowsRecyclerView.apply {
            layoutParams =
                ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 800).apply {
                    topToBottom = tvShowsTitle.id
                    startToStart = conLayout.id
                    endToEnd = conLayout.id
                    setBackgroundColor(Color.GRAY)
                }
        }
        tvShowsRecyclerView.addItemDecoration(MyItemDecoration(10, 10, 10, 0))
        moviesTitle.apply {
            layoutParams = ConstraintLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(20, 20, 20, 20)
                topToBottom = tvShowsRecyclerView.id
                startToStart = conLayout.id
                endToEnd = conLayout.id
                setBackgroundColor(Color.GRAY)
            }
        }
        moviesRecyclerView.apply {
            layoutParams =
                ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 800).apply {
                    topToBottom = moviesTitle.id
                    startToStart = conLayout.id
                    endToEnd = conLayout.id
                    setBackgroundColor(Color.GRAY)
                }
        }
        moviesRecyclerView.addItemDecoration(MyItemDecoration(20, 20, 10, 0))

        conLayout.addView(tvShowsTitle)
        conLayout.addView(tvShowsRecyclerView)
        conLayout.addView(moviesTitle)
        conLayout.addView(moviesRecyclerView)


    }
}
