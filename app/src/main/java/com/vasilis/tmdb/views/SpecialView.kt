package com.vasilis.tmdb.views

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.vasilis.tmdb.MyItemDecoration


class SpecialView(context: Context):ConstraintLayout(context) {
    var title=TextView(context).apply {
        id = View.generateViewId()
        typeface = Typeface.DEFAULT_BOLD
        setTextColor(Color.BLACK)
        textSize = 14F
    }
    var unknown=TextView(context).apply {
        id = View.generateViewId()
        text = "Unknown"
    }
        var recyclerView=RecyclerView(context).apply{id=View.generateViewId()}
        init{
            //apply{id=View.generateViewId()}
            recyclerView.addItemDecoration(MyItemDecoration(10,10,10,10))
            layoutParams= LayoutParams(MATCH_PARENT, WRAP_CONTENT)
            title.apply {
                layoutParams=ConstraintLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT).apply {
                    topToTop=this@SpecialView.id
                    startToStart=this@SpecialView.id
                    endToEnd=this@SpecialView.id
                }
            }
            unknown.apply {
                layoutParams=ConstraintLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT).apply {
                    topToBottom=title.id
                    startToStart=this@SpecialView.id
                    endToEnd=this@SpecialView.id
                }
            }
            recyclerView.apply {
                layoutParams=ConstraintLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT).apply {
                    topToBottom=title.id
                    startToStart=this@SpecialView.id
                    endToEnd=this@SpecialView.id
                }
            }
            addView(title)
//        addView(unknown)
//        addView(recyclerView)
        }
    }
