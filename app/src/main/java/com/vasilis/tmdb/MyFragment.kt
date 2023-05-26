package com.vasilis.tmdb

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vasilis.tmdb.adapters.VPAdapter
import com.vasilis.tmdb.views.MyView
import com.google.android.material.tabs.TabLayoutMediator

class MyFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.context.let {
        MyView(it).apply {
//val shared = PreferenceManager.getDefaultSharedPreferences(context)
//shared.edit().clear().apply()
            var vpAdapter = VPAdapter(this@MyFragment)
            viewPager.adapter = vpAdapter

            TabLayoutMediator(
                tabLayout, viewPager
            ) { tab, position ->
                when (position) {
                    0 -> tab.text = "Top Rated Shows"
                    1 -> tab.text = "Top Rated Movies"
                    2 -> tab.text = "Search Movies-TV Shows"
                    3 -> tab.text = "Favorites"
                }
            }.attach()
        }
    }
}