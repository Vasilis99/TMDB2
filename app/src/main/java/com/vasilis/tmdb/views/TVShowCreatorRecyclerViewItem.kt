package com.vasilis.tmdb.views

import android.content.Context
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout


class TVShowCreatorRecyclerViewItem(context: Context) : ConstraintLayout(context) {
    var name = TextView(context).apply{id= View.generateViewId()}
    var photo = ImageView(context).apply{id=View.generateViewId()}

    init {
        layoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        this@TVShowCreatorRecyclerViewItem.apply{id=View.generateViewId()}
        name.layoutParams = ConstraintLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT).apply {
            topToTop = this@TVShowCreatorRecyclerViewItem.id
            startToStart = this@TVShowCreatorRecyclerViewItem.id
            endToEnd = this@TVShowCreatorRecyclerViewItem.id
        }

        photo.layoutParams = ConstraintLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT).apply {
            topToBottom = name.id
            startToStart = this@TVShowCreatorRecyclerViewItem.id
            endToEnd = this@TVShowCreatorRecyclerViewItem.id
        }
        addView(name)
        addView(photo)
    }
}