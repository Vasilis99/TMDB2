package com.vasilis.tmdb

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.vasilis.tmdb.top_rated_movies.TopRatedMoviesFragment

class FragmentsViewModel(application: Application) : AndroidViewModel(application) {
    var fragmentLiveData=MutableLiveData<Fragment?>(null)


    fun addFragment(fragment: Fragment) {
        if (fragment is TopRatedMoviesFragment)
            fragmentLiveData = MutableLiveData<Fragment?>(fragment)
    }

    fun getFragment(): Fragment? {
        return fragmentLiveData.value
    }
}