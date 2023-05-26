package com.vasilis.tmdb.views

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.vasilis.tmdb.R

class RecyclerViewItemView(context: Context) : ConstraintLayout(context) {
    var image= ImageView(context).apply{id=View.generateViewId()}
    var conLayBig=ConstraintLayout(context).apply{id=View.generateViewId()}
    var conLayFirst=ConstraintLayout(context).apply{id=View.generateViewId()}
    var pos=TextView(context).apply{id=View.generateViewId()}
    var title=TextView(context).apply{
        id=View.generateViewId()
        setTextColor(Color.BLACK)
        textSize=14F
    }
    var conLaySecond=ConstraintLayout(context).apply{id=View.generateViewId()}
    var star=ImageView(context).apply {
        id = View.generateViewId()
        setImageResource(R.drawable.star_rate)
    }
    var rating=TextView(context).apply{id=View.generateViewId()}
    var overview=TextView(context).apply{id=View.generateViewId()}
    var voteCount=TextView(context).apply{id=View.generateViewId()}
    var favorite=CheckBox(context).apply{id=View.generateViewId()}.apply{setButtonDrawable(R.drawable.selector_favorite)}


    init{
        id= View.generateViewId()
        layoutParams= LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        image.layoutParams=LayoutParams(342, 342*3/2).apply {
            topToTop=this@RecyclerViewItemView.id
            bottomToBottom=this@RecyclerViewItemView.id
            startToStart=this@RecyclerViewItemView.id
        }
        addView(image)
        conLayBig.layoutParams= LayoutParams(0, WRAP_CONTENT).apply {
            topToTop=this@RecyclerViewItemView.id
            bottomToBottom=this@RecyclerViewItemView.id
            startToEnd=image.id
            endToEnd=this@RecyclerViewItemView.id
            setMargins(20,0,20,0)
        }
        addView(conLayBig)
        conLayFirst.layoutParams= LayoutParams(MATCH_PARENT, WRAP_CONTENT).apply {
            topToTop=conLayBig.id
            startToStart=conLayBig.id
            endToEnd=conLayBig.id
            setMargins(0,0,24,0)
        }

        conLayBig.addView(conLayFirst)
        pos.layoutParams= LayoutParams(WRAP_CONTENT, WRAP_CONTENT).apply {
            topToTop=conLayFirst.id
            bottomToBottom=conLayFirst.id
            startToStart=conLayFirst.id
        }
        conLayFirst.addView(pos)
        title.layoutParams= LayoutParams(0, WRAP_CONTENT).apply {
            topToTop=conLayFirst.id
            bottomToBottom=conLayFirst.id
            startToEnd=pos.id
            endToEnd=conLayFirst.id
        }
        conLayFirst.addView(title)


        favorite.layoutParams= LayoutParams(WRAP_CONTENT, WRAP_CONTENT).apply {
            topToTop=this@RecyclerViewItemView.id
            endToEnd=this@RecyclerViewItemView.id
            setMargins(0,20,20,0)
        }
        addView(favorite)
        conLaySecond.layoutParams= LayoutParams(MATCH_PARENT, WRAP_CONTENT).apply {
            topToBottom=conLayFirst.id
            startToStart=conLayBig.id
        }
        conLayBig.addView(conLaySecond)
        star.layoutParams= LayoutParams(WRAP_CONTENT, WRAP_CONTENT).apply {
            topToTop=conLaySecond.id
            bottomToBottom=conLaySecond.id
            startToStart=conLaySecond.id
        }
        conLaySecond.addView(star)

        rating.layoutParams= LayoutParams(70, WRAP_CONTENT).apply {
            topToTop=conLaySecond.id
            bottomToBottom=conLaySecond.id
            startToEnd=star.id
        }
        conLaySecond.addView(rating)

        voteCount.layoutParams= LayoutParams(WRAP_CONTENT, WRAP_CONTENT).apply {
            topToTop=conLaySecond.id
            bottomToBottom=conLaySecond.id
            startToEnd=rating.id
            marginStart=20

        }
        conLaySecond.addView(voteCount)

        overview.layoutParams= LayoutParams(MATCH_PARENT, WRAP_CONTENT).apply {
            topToBottom=conLaySecond.id
            startToStart=conLayBig.id
            bottomToBottom=conLayBig.id
        }
        conLayBig.addView(overview)
    }
}