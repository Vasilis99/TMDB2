package com.vasilis.tmdb.fragments

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

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