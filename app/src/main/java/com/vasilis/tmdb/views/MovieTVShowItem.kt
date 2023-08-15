package com.vasilis.tmdb.views

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.vasilis.tmdb.R

class MovieTVShowItem(context: Context) : ConstraintLayout(context) {
    var image = ImageView(context).apply { id = View.generateViewId() }
    var conLayFirst = ConstraintLayout(context).apply { id = View.generateViewId() }
    var pos = TextView(context).apply { id = View.generateViewId() }
    var title = TextView(context).apply {
        id = View.generateViewId()
        setTextColor(Color.BLACK)
        textSize = 14F
    }
    var conLaySecond = ConstraintLayout(context).apply { id = View.generateViewId() }
    var star = ImageView(context).apply {
        id = View.generateViewId()
        setImageResource(R.drawable.star_rate)
    }
    var rating = TextView(context).apply { id = View.generateViewId() }
    var overview = TextView(context).apply { id = View.generateViewId() }
    var voteCount = TextView(context).apply { id = View.generateViewId() }
    var favorite = CheckBox(context).apply { id = View.generateViewId() }.apply { setButtonDrawable(R.drawable.selector_favorite) }

    init {
        id = View.generateViewId()
        layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        image.layoutParams = LayoutParams(342, 342 * 3 / 2).apply {
            topToTop = this@MovieTVShowItem.id
            bottomToBottom = this@MovieTVShowItem.id
            startToStart = this@MovieTVShowItem.id
        }
        addView(image)

        conLayFirst.layoutParams = LayoutParams(0, WRAP_CONTENT).apply {
            topToTop = this@MovieTVShowItem.id
            startToEnd = image.id
            endToStart = favorite.id
            setMargins(10, 0, 10, 0)
        }

        addView(conLayFirst)
        pos.layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT).apply {
            topToTop = conLayFirst.id
            bottomToBottom = conLayFirst.id
            startToStart = conLayFirst.id
        }
        conLayFirst.addView(pos)
        title.layoutParams = LayoutParams(0, WRAP_CONTENT).apply {
            topToTop = conLayFirst.id
            bottomToBottom = conLayFirst.id
            startToEnd = pos.id
            endToEnd = conLayFirst.id
        }
        conLayFirst.addView(title)

        favorite.layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT).apply {
            topToTop = this@MovieTVShowItem.id
            endToEnd = this@MovieTVShowItem.id
            setMargins(0, 20, 20, 0)
        }
        addView(favorite)
        conLaySecond.layoutParams = LayoutParams(0, WRAP_CONTENT).apply {
            topToBottom = conLayFirst.id
            startToEnd = image.id
            endToStart = favorite.id
            setMargins(10, 0, 10, 0)
        }
        addView(conLaySecond)
        star.layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT).apply {
            topToTop = conLaySecond.id
            bottomToBottom = conLaySecond.id
            startToStart = conLaySecond.id
        }
        conLaySecond.addView(star)

        rating.layoutParams = LayoutParams(70, WRAP_CONTENT).apply {
            topToTop = conLaySecond.id
            bottomToBottom = conLaySecond.id
            startToEnd = star.id
        }
        conLaySecond.addView(rating)

        voteCount.layoutParams = LayoutParams(0, WRAP_CONTENT).apply {
            topToTop = conLaySecond.id
            bottomToBottom = conLaySecond.id
            startToEnd = rating.id
            setMargins(20, 0, 0, 0)
        }
        conLaySecond.addView(voteCount)

        overview.layoutParams = LayoutParams(0, WRAP_CONTENT).apply {
            topToBottom = conLaySecond.id
            startToEnd = image.id
            endToEnd = this@MovieTVShowItem.id
            setMargins(10, 0, 0, 0)
        }
        addView(overview)
    }
}
