package com.vasilis.tmdb

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    lateinit var myLayout: FrameLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myLayout = FrameLayout(this)
        myLayout.id = View.generateViewId()
        setContentView(myLayout)
        var myFragment = MyFragment()
        supportFragmentManager
            .beginTransaction().replace(myLayout.id, myFragment)
            // .apply { if (backStack) addToBackStack(null) }
            .commit()

//        val latestFragment= ViewModelProvider(this)[FragmentsViewModel::class.java]
//        if(latestFragment.getFragment()!=null) {
//            latestFragment.fragmentLiveData.value?.let {
//                supportFragmentManager.beginTransaction().replace(myLayout.id, it)
//                    // .apply { if (backStack) addToBackStack(null) }
//                    .commit()
//            }
//        }
//        else{
//            println("Einai null to live data")
//        }

//        tabLayout= TabLayout(this)
//        tabLayout.id=View.generateViewId()
//
// //        tabLayout.addTab(tabLayout.newTab().setText("Tabsd 1"))
// //        tabLayout.addTab(tabLayout.newTab().setText("Tab 2"))
// //        tabLayout.addTab(tabLayout.newTab().setText("Tab 3"))
// //        myLayout.addView(tabLayout)
//
//        viewPager=ViewPager2(this)
//        myLayout.addView(tabLayout)
//        myLayout.addView(viewPager)
//
//        var vpAdapter = VPAdapter(this)
//        viewPager.adapter=vpAdapter
//
//        TabLayoutMediator(
//            tabLayout,
//            viewPager
//        ) { tab, position -> when (position){
//          0-> tab.text = "Top Rated Shows"
//          1-> tab.text = "Top Rated Movies"
//            2-> tab.text = "Search Movies-TV Shows"
//        }}.attach()
    }

    override fun onDestroy() {
        super.onDestroy()
        println("Activity destroyed")
    }
}
