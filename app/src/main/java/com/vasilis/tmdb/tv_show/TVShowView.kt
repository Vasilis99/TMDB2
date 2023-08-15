package com.vasilis.tmdb.tv_show

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.*
import android.widget.ImageView.ScaleType
import androidx.recyclerview.widget.RecyclerView
import com.vasilis.tmdb.MyItemDecoration
import com.vasilis.tmdb.views.SpecialView
import com.vasilis.tmdb.views.TitleDescriptionView

class TVShowView(context: Context) : ScrollView(context) {
    var tvShowViews: MutableList<View> = mutableListOf()
    var linearLayout = LinearLayout(context).apply { id = View.generateViewId() }
    var recyclerView = RecyclerView(context).apply { id = View.generateViewId() }
    var tvShowsShimmer = TVShowsShimmer(context)

    init {
        apply { id = View.generateViewId() }
        this.setBackgroundColor(Color.WHITE)
        recyclerView.layoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT)
        recyclerView.addItemDecoration(MyItemDecoration(10, 10, 0, 20))

        tvShowViews.add(
            TextView(context).apply {
                id = View.generateViewId()
                typeface = Typeface.DEFAULT_BOLD
                setTextColor(Color.BLACK)
                textSize = 22F
            }
        )

        tvShowViews.add(
            ImageView(context).apply {
                id = View.generateViewId()
                scaleType = ScaleType.FIT_CENTER
            }
        )
        tvShowViews.add(SpecialView(context))

        for (i in 3..10) {
            tvShowViews.add(TitleDescriptionView(context).vertical())
        }
        tvShowViews.add(TVShowLastEpisodeView(context))

        for (i in 12..18) {
            tvShowViews.add(TitleDescriptionView(context).vertical())
        }
        tvShowViews.add(ImageView(context).apply { id = View.generateViewId() })
        tvShowViews.add(SpecialView(context))

        for (i in 21..27) {
            tvShowViews.add(TitleDescriptionView(context).vertical())
        }

        tvShowViews.add(
            Button(context).apply {
                id = View.generateViewId()
                text = "Reviews"
                layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
            }
        )
        linearLayout.apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT).apply {
                setMargins(10, 0, 10, 20)
            }
        }
        tvShowViews.forEach {
            linearLayout.addView(it)
        }
//        recyclerView.vLinear.lazyAdd {
//            for (i in 0..28) {
//                if (i == 1) {
//                    add(tvShowViews[i].bind {
//                        height = 596
//                        width = MATCH_PARENT
//                    })
//                } else if (i == 19) {
//                    add(tvShowViews[i].bind {
//                        height = 1590
//                        width = 1060
//                    })
//                } else {
//                    add(tvShowViews[i])
//                }
//            }
//        }

        addView(tvShowsShimmer)
    }
}
