package com.vasilis.tmdb.views

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class CollectionView(context: Context) : ConstraintLayout(context) {
    var title = TextView(context).apply {
        id = View.generateViewId()
        typeface = Typeface.DEFAULT_BOLD
        setTextColor(Color.BLACK)
        textSize = 14F
    }
    var name = TextView(context).apply {
        id = View.generateViewId()
        typeface = Typeface.DEFAULT_BOLD
        setTextColor(Color.BLACK)
    }
    var unknown = TextView(context).apply { id = View.generateViewId() }
    var image = ImageView(context).apply { id = View.generateViewId() }

    init {
        apply { id = View.generateViewId() }
        layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        title.layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT).apply {
            topToTop = this@CollectionView.id
            startToStart = this@CollectionView.id
            endToEnd = this@CollectionView.id
        }
        addView(title)

        name.layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT).apply {
            topToBottom = title.id
            startToStart = this@CollectionView.id
            endToEnd = this@CollectionView.id
        }

        image.layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT).apply {
            topToBottom = name.id
            startToStart = this@CollectionView.id
        }

        unknown.layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT).apply {
            topToBottom = title.id
            startToStart = this@CollectionView.id
            endToEnd = this@CollectionView.id
        }
    }
}
