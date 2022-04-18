package com.sp.corefeatures

import android.os.Bundle
import androidx.navigation.NavOptions
import androidx.navigation.fragment.FragmentNavigator

data class NavDestination(
    val destination: Int,
    val bundle: Bundle? = null,
    val navOptions: NavOptions? = null,
    val navExtras: FragmentNavigator.Extras? = null
)
