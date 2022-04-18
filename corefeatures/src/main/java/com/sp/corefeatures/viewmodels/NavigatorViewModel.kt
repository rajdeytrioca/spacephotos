package com.sp.corefeatures.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sp.corefeatures.NavDestination

class NavigatorViewModel : ViewModel() {

    private val _fragmentDestination = MutableLiveData<NavDestination>()

    /**
     * LiveData which tells the parent which fragment to load
     */
    val fragmentDestination: LiveData<NavDestination> = _fragmentDestination

    /**
     * Handle the navigation to the given [navDestination].
     */
    fun navigateTo(navDestination: NavDestination) {
        _fragmentDestination.postValue(navDestination)
    }
}