package com.vasilis.tmdb.movie

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.facebook.shimmer.ShimmerFrameLayout

class MovieShimmer(context: Context) : ShimmerFrameLayout(context) {
    var tvShowViews: MutableList<View> = mutableListOf()
    var linearLayout = LinearLayout(context).apply { id = View.generateViewId() }

    private fun myShimmer(): ConstraintLayout {
        var conLay = ConstraintLayout(context).apply { id = View.generateViewId() }
        var title = TextView(context).apply {
            id = View.generateViewId()
            textSize = 14F
        }
        var desc = TextView(context).apply { id = View.generateViewId() }
        title.setBackgroundColor(Color.GRAY)
        title.layoutParams = ConstraintLayout.LayoutParams(200, WRAP_CONTENT).apply {
            topToTop = conLay.id
            startToStart = conLay.id
        }
        desc.setBackgroundColor(Color.GRAY)
        desc.layoutParams = ConstraintLayout.LayoutParams(200, WRAP_CONTENT).apply {
            topToBottom = title.id
            bottomToBottom = conLay.id
            startToStart = conLay.id
            setMargins(0, 10, 0, 0)
        }
        conLay.addView(title)
        conLay.addView(desc)
        return conLay
    }

    private fun creators(): ConstraintLayout {
        var conLay = ConstraintLayout(context).apply { id = View.generateViewId() }.apply {
            layoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        }
        var cTitle = TextView(context).apply {
            id = View.generateViewId()
            setBackgroundColor(Color.GRAY)
            textSize = 14F
            layoutParams = ConstraintLayout.LayoutParams(100, WRAP_CONTENT).apply {
                topToTop = conLay.id
                startToStart - conLay.id
            }
        }
        var linLayout = LinearLayout(context).apply { id = View.generateViewId() }.apply {
            orientation = LinearLayout.HORIZONTAL
            layoutParams = ConstraintLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT).apply {
                topToBottom = cTitle.id
                startToStart = conLay.id
                endToEnd = conLay.id
            }
        }

        for (i in 0..3) {
            var creator = ConstraintLayout(context).apply { id = View.generateViewId() }.apply {
                layoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT).apply {
                    setMargins(0, 0, 10, 0)
                }
            }
            var name = TextView(context).apply { id = View.generateViewId() }.apply {
                setBackgroundColor(Color.GRAY)
                layoutParams = ConstraintLayout.LayoutParams(80, WRAP_CONTENT).apply {
                    topToTop = creator.id
                    startToStart = creator.id
                    endToEnd = creator.id
                }
            }
            var photo = ConstraintLayout(context).apply { id = View.generateViewId() }.apply {
                setBackgroundColor(Color.BLACK)
                layoutParams = ConstraintLayout.LayoutParams(185, 300).apply {
                    topToBottom = name.id
                    startToStart = creator.id
                    endToEnd = creator.id
                    minHeight = 50
                    setMargins(0, 10, 0, 0)
                }
            }
            creator.addView(name)
            creator.addView(photo)
            linLayout.addView(creator)
        }

        conLay.addView(cTitle)
        conLay.addView(linLayout)

        return conLay
    }

    init {
        apply { id = View.generateViewId() }
        layoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT)

        tvShowViews.add(
            TextView(context).apply {
                id = View.generateViewId()
                textSize = 22F
                setBackgroundColor(Color.GRAY)
            }
        )

        tvShowViews.add(myShimmer())

        tvShowViews.add(
            ConstraintLayout(context).apply {
                id = View.generateViewId()
            }.apply {
                setBackgroundColor(Color.GRAY)
                layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
            }
        )
        // tvShowViews.add(creators())

        for (i in 3..10) {
            tvShowViews.add(myShimmer())
        }
        var params1 = LinearLayout.LayoutParams(800, WRAP_CONTENT)
        var params2 = LinearLayout.LayoutParams(MATCH_PARENT, 596)

        linearLayout.addView(tvShowViews[0])
        linearLayout.addView(tvShowViews[1], params1)
        linearLayout.addView(tvShowViews[2], params2)
        for (i in 3..tvShowViews.size) {
            linearLayout.addView(tvShowViews[i])
        }

        addView(linearLayout)
    }
}
