package com.vasilis.tmdb.views

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.vasilis.tmdb.views.shimmer.SearchMovieTVShowShimmer


class SearchMovieTVShowView(context: Context) : ConstraintLayout(context) {
    var title = TextView(context).apply {
        id = View.generateViewId()
        typeface = Typeface.DEFAULT_BOLD
        setTextColor(Color.BLACK)
        text = "Search Movies-TV Shows"
        textSize = 20F
    }
    var tvShowInputBox = EditText(context).apply { id = View.generateViewId() }
    var tvShowButton = Button(context).apply {
        id = View.generateViewId()
        text = "Search TV Show"
    }
    var movieInputBox = EditText(context).apply { id = View.generateViewId() }
    var movieButton = Button(context).apply {
        id = View.generateViewId()
        text = "Search Movie"
    }
    var shimmer = SearchMovieTVShowShimmer(context)

    init {
        id = generateViewId()
        layoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT)

        title.layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT).apply {
            topToTop = this@SearchMovieTVShowView.id
            startToStart = this@SearchMovieTVShowView.id
            endToEnd = this@SearchMovieTVShowView.id
            setMargins(20, 0, 20, 0)
        }

        addView(title)
        tvShowInputBox.layoutParams = LayoutParams(400, WRAP_CONTENT).apply {
            topToBottom = title.id
            startToStart = this@SearchMovieTVShowView.id
            endToStart = tvShowButton.id
            setMargins(0, 20, 0, 0)
        }

        addView(tvShowInputBox)

        tvShowButton.layoutParams = LayoutParams(400, WRAP_CONTENT).apply {
            topToBottom = title.id
            endToEnd = this@SearchMovieTVShowView.id
            startToEnd = tvShowInputBox.id
            setMargins(0, 20, 0, 0)
        }

        addView(tvShowButton)
        movieInputBox.layoutParams = LayoutParams(400, WRAP_CONTENT).apply {
            topToBottom = tvShowInputBox.id
            startToStart = this@SearchMovieTVShowView.id
            endToStart = movieButton.id
        }

        addView(movieInputBox)


        movieButton.layoutParams = LayoutParams(400, WRAP_CONTENT).apply {
            topToBottom = tvShowButton.id
            startToEnd = movieInputBox.id
            endToEnd = this@SearchMovieTVShowView.id
        }
        addView(movieButton)
        addView(shimmer)
        visibility = INVISIBLE
    }
}
