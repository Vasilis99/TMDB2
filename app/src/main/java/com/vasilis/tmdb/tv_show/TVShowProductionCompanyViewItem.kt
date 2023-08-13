package com.vasilis.tmdb.tv_show

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout


class TVShowProductionCompanyViewItem(context: Context) : ConstraintLayout(context) {
    var name = TextView(context).apply {
        id = View.generateViewId()
        setTextColor(Color.BLACK)
    }
    var image = ImageView(context).apply { id = View.generateViewId() }

    init {
        layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        id = generateViewId()
        name.layoutParams = ConstraintLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT).apply {
            topToTop = this@TVShowProductionCompanyViewItem.id
            startToStart = this@TVShowProductionCompanyViewItem.id
            endToEnd = this@TVShowProductionCompanyViewItem.id
        }
        addView(name)
        image.layoutParams = ConstraintLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT).apply {
            topToBottom = name.id
            startToStart = this@TVShowProductionCompanyViewItem.id
            endToEnd = this@TVShowProductionCompanyViewItem.id
        }
        addView(image)
    }
}
