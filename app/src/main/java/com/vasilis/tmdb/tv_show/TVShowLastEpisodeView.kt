package com.vasilis.tmdb.tv_show

import android.graphics.Color
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.TextView


import android.content.Context
import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import android.widget.HorizontalScrollView
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.vasilis.tmdb.views.TitleDescriptionView


class TVShowLastEpisodeView(context: Context) : ConstraintLayout(context) {
    var title=TextView(context).apply {
        id = View.generateViewId()
        typeface= Typeface.DEFAULT_BOLD
        setTextColor(Color.BLACK)
        textSize=14F
    }
    var scrollView=HorizontalScrollView(context).apply{id=View.generateViewId()}
    var smallConLayout = ConstraintLayout(context).apply{id=View.generateViewId()}
    var linLayout = LinearLayout(context).apply{id=View.generateViewId()}
    var overview = TitleDescriptionView(context).vertical()
    var image = ImageView(context).apply{id=View.generateViewId()}
    var unknown = TextView(context).apply {
        id = View.generateViewId()
        setTextColor(Color.BLACK)
        text="Unknown"
    }
    var lastEpViews: MutableList<View> = mutableListOf()

    init {
        id = generateViewId()
        layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        title.layoutParams=ConstraintLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT).apply {
            topToTop=this@TVShowLastEpisodeView.id
            startToStart=this@TVShowLastEpisodeView.id
            endToEnd=this@TVShowLastEpisodeView.id
        }
        addView(title)
        scrollView.layoutParams=ConstraintLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT).apply {
            topToBottom=title.id
            bottomToBottom=this@TVShowLastEpisodeView.id
            startToStart=this@TVShowLastEpisodeView.id
            endToEnd=this@TVShowLastEpisodeView.id
        }
        scrollView.addView(smallConLayout)
        smallConLayout.layoutParams=FrameLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT)

        linLayout.orientation = LinearLayout.VERTICAL
        linLayout.layoutParams = ConstraintLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT).apply {
            topToTop = smallConLayout.id
            startToStart = smallConLayout.id
        }
        smallConLayout.addView(linLayout)
        for (i in 0..5) {
            var titleDescription= TitleDescriptionView(context).horizontal()
            titleDescription.title.apply {
                textSize=12F
            }
            lastEpViews.add(titleDescription)
        }

        for (x in lastEpViews) {
            linLayout.addView(x)
        }
        overview.title.apply {
            textSize=12F
        }
        overview.layoutParams = ConstraintLayout.LayoutParams(800, WRAP_CONTENT).apply {
            topToTop = smallConLayout.id
            startToEnd = linLayout.id
            setMargins(20,0,0,0)
        }
        smallConLayout.addView(overview)

        image.layoutParams = ConstraintLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT).apply {
            topToTop = smallConLayout.id
            startToEnd = overview.id
            setMargins(20,0,0,0)
        }
        smallConLayout.addView(image)
        unknown.layoutParams=ConstraintLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT).apply {
            topToBottom=title.id
            bottomToBottom=this@TVShowLastEpisodeView.id
            startToStart=this@TVShowLastEpisodeView.id
            endToEnd=this@TVShowLastEpisodeView.id
        }
    }

}