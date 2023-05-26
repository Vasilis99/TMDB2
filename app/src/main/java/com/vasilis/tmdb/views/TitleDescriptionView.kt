package com.vasilis.tmdb.views

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout


class TitleDescriptionView(context: Context):ConstraintLayout(context){
    var title=TextView(context).apply {
        typeface= Typeface.DEFAULT_BOLD
        setTextColor(Color.BLACK)
        textSize=14F
    }
        var desc=TextView(context)
        fun vertical(): TitleDescriptionView {
            title.layoutParams=ConstraintLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT).apply {
                topToTop=this@TitleDescriptionView.id
                startToStart=this@TitleDescriptionView.id

            }
            desc.layoutParams=ConstraintLayout.LayoutParams(MATCH_PARENT,WRAP_CONTENT).apply {
                topToBottom=title.id
                bottomToBottom=this@TitleDescriptionView.id
                startToStart=this@TitleDescriptionView.id

            }
            return this
        }
        fun horizontal(): TitleDescriptionView {
            title.layoutParams=ConstraintLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT).apply {
                topToTop=this@TitleDescriptionView.id
                bottomToBottom=this@TitleDescriptionView.id
                startToStart=this@TitleDescriptionView.id
            }
            desc.layoutParams=ConstraintLayout.LayoutParams(WRAP_CONTENT,WRAP_CONTENT).apply {
                topToTop=this@TitleDescriptionView.id
                bottomToBottom=this@TitleDescriptionView.id
                startToEnd=title.id
                marginStart=20
            }
            return this
        }
        init{
            id= View.generateViewId()
            layoutParams= LayoutParams(WRAP_CONTENT,WRAP_CONTENT)
            title.id=View.generateViewId()
            desc.id=View.generateViewId()
            addView(title)
            addView(desc)
        }
    }
