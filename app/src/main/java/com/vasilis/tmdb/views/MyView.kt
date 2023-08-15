package com.vasilis.tmdb.views

import android.content.Context
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout

class MyView(context: Context) : LinearLayout(context) {
    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager2

    init {
        orientation = VERTICAL
        tabLayout = TabLayout(context)
        tabLayout.id = View.generateViewId()

//        tabLayout.addTab(tabLayout.newTab().setText("Tabsd 1"))
//        tabLayout.addTab(tabLayout.newTab().setText("Tab 2"))
//        tabLayout.addTab(tabLayout.newTab().setText("Tab 3"))
//        myLayout.addView(tabLayout)

        viewPager = ViewPager2(context)
        viewPager.layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
        addView(tabLayout)
        addView(viewPager)
    }
}
