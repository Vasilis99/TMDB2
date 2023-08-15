package com.vasilis.tmdb.search

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.facebook.shimmer.ShimmerFrameLayout

class SearchMovieTVShowShimmer(context: Context) : ShimmerFrameLayout(context) {
    var title = TextView(context).apply {
        id = View.generateViewId()
        textSize = 20F
    }
    var tvShowInputBox = EditText(context).apply { id = View.generateViewId() }
    var tvShowButton = Button(context).apply { id = View.generateViewId() }
    var movieInputBox = EditText(context).apply { id = View.generateViewId() }
    var movieButton = Button(context).apply { id = View.generateViewId() }
    var conLay = ConstraintLayout(context).apply { id = View.generateViewId() }
    init {
        apply { id = View.generateViewId() }

        layoutParams = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

        title.layoutParams = ConstraintLayout.LayoutParams(400, WRAP_CONTENT).apply {
            topToTop = conLay.id
            startToStart = conLay.id
            endToEnd = conLay.id
            setMargins(20, 0, 20, 0)
            setBackgroundColor(Color.GRAY)
        }

        conLay.addView(title)
        tvShowInputBox.layoutParams = ConstraintLayout.LayoutParams(400, WRAP_CONTENT).apply {
            topToBottom = title.id
            startToStart = conLay.id
            endToStart = tvShowButton.id
            setMargins(0, 20, 0, 0)
            setBackgroundColor(Color.GRAY)
        }

        conLay.addView(tvShowInputBox)

        tvShowButton.layoutParams = ConstraintLayout.LayoutParams(400, WRAP_CONTENT).apply {
            topToBottom = title.id
            endToEnd = conLay.id
            startToEnd = tvShowInputBox.id
            setMargins(0, 20, 0, 0)
            setBackgroundColor(Color.GRAY)
        }

        conLay.addView(tvShowButton)
        movieInputBox.layoutParams = ConstraintLayout.LayoutParams(400, WRAP_CONTENT).apply {
            topToBottom = tvShowInputBox.id
            startToStart = conLay.id
            endToStart = movieButton.id
            setBackgroundColor(Color.GRAY)
        }

        conLay.addView(movieInputBox)

        movieButton.layoutParams = ConstraintLayout.LayoutParams(400, WRAP_CONTENT).apply {
            topToBottom = tvShowButton.id
            startToEnd = movieInputBox.id
            endToEnd = conLay.id
        }
        conLay.addView(movieButton)
        addView(conLay)
    }
}
