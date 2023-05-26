package com.vasilis.tmdb.views.shimmer
//
//import android.content.Context
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class MyKoleton(context: Context) : ConstraintLayout(context) {
    init {
        id = View.generateViewId()

        var image = ImageView(context).apply { id = View.generateViewId() }

        var secConLay = ConstraintLayout(context).apply {
            id = View.generateViewId()
            setBackgroundColor(Color.GRAY)
        }
        var title = TextView(context).apply {
            id = View.generateViewId()
            setTextColor(Color.BLACK)
            typeface = Typeface.DEFAULT_BOLD
            textSize = 14F
        }
        var favorite = TextView(context).apply { id = View.generateViewId() }
        layoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT)
        secConLay.layoutParams = ConstraintLayout.LayoutParams(0, WRAP_CONTENT).apply {
            topToTop = this@MyKoleton.id
            bottomToBottom = this@MyKoleton.id
            startToEnd = image.id
            endToEnd = this@MyKoleton.id
            setMargins(10, 10, 10, 10)
        }
        image.apply {
            setBackgroundColor(Color.BLACK)
            layoutParams = ConstraintLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT).apply {
                topToTop = this@MyKoleton.id
                bottomToBottom = this@MyKoleton.id
                startToStart = this@MyKoleton.id
                minimumHeight = 528
                minimumWidth = 342
            }
        }

        addView(image)

        title.apply {
            setBackgroundColor(Color.BLACK)
            layoutParams = ConstraintLayout.LayoutParams(0, WRAP_CONTENT).apply {
                topToTop = secConLay.id
                startToStart = secConLay.id
                endToStart = favorite.id
                setMargins(0, 0, 10, 0)
            }
        }
        secConLay.addView(title)
        var votesRating = TextView(context).apply { id = View.generateViewId() }.apply {
            setBackgroundColor(Color.BLACK)
            layoutParams = ConstraintLayout.LayoutParams(200, WRAP_CONTENT).apply {
                topToBottom = title.id
                startToEnd = image.id
                setMargins(0, 10, 0, 0)
            }
        }
        secConLay.addView(votesRating)
        var temp = TextView(context)
        for (i in 0..4) {
            var text = TextView(context).apply { id = View.generateViewId() }.apply {
                setBackgroundColor(Color.BLACK)
            }
            text.layoutParams = ConstraintLayout.LayoutParams(0, WRAP_CONTENT).apply {
                topToBottom = if (i == 0) {
                    votesRating.id
                } else {
                    temp.id
                }
                startToStart = secConLay.id
                endToEnd = secConLay.id
                setMargins(0, 10, 0, 0)
            }
            secConLay.addView(text)
            temp = text
        }

        favorite.apply {
            setBackgroundColor(Color.BLACK)
            layoutParams = ConstraintLayout.LayoutParams(24, 24).apply {
                endToEnd = secConLay.id
            }
        }
        secConLay.addView(favorite)
        addView(secConLay)
    }
}
