package com.vasilis.tmdb.views

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class MoviesDetailItem(context: Context) : LinearLayout(context) {

    var title = TextView(context).apply {
        textSize = 20F
        setTextColor(Color.BLACK)
        typeface = Typeface.DEFAULT_BOLD
    }
    var text = TextView(context).apply {
        textSize = 20F
        setTextColor(Color.BLACK)
    }
    var image = ImageView(context)

    init {
        // LinearLayout.LayoutParams.MATCH_PARENT
        orientation = VERTICAL
        addView(title)
        addView(text)
        addView(image)
        image.layoutParams.width = 500
    }
}
