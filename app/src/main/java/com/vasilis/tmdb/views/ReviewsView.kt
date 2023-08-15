package com.vasilis.tmdb.views

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView

class ReviewsView(context: Context) : ScrollView(context) {
    class ReviewView(cont: Context) : LinearLayout(cont) {
        var author = TitleDescriptionView(context).vertical()
        var content = TitleDescriptionView(context).vertical()
        var createdAt = TitleDescriptionView(context).vertical()
        var updatedAt = TitleDescriptionView(context).vertical()
        var url = TitleDescriptionView(context).vertical()
        init {
            orientation = LinearLayout.VERTICAL
            id = View.generateViewId()
            layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
            var linLayParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT).apply {
                setMargins(10, 0, 10, 20)
            }
            addView(author, linLayParams)
            addView(content, linLayParams)
            addView(createdAt, linLayParams)
            addView(updatedAt, linLayParams)
            addView(
                url.apply {
                    layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT).apply {
                        setMargins(10, 0, 10, 0)
                    }
                }
            )
        }
    }
    var linLayout = LinearLayout(context)
    var title = TextView(context).apply {
        id = View.generateViewId()
        typeface = Typeface.DEFAULT_BOLD
        setTextColor(Color.BLACK)
        textSize = 20F
    }
    init {
        apply { id = View.generateViewId() }
        this.setBackgroundColor(Color.WHITE)
        addView(linLayout)

        layoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT)
        linLayout.apply {
            orientation = LinearLayout.VERTICAL
            id = View.generateViewId()
            layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT).apply {
                setMargins(20, 0, 20, 0)
            }
        }
        linLayout.addView(title)
        title.layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
    }
}
