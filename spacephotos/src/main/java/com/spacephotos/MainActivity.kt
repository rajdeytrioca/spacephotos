package com.spacephotos

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.sp.corefeatures.NavDestination
import com.sp.corefeatures.viewmodels.NavigatorViewModel
import com.spacephotos.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    private lateinit var searchBinding: ActivityMainBinding
    private lateinit var navController: NavController
    private val navigatorViewModel: NavigatorViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(searchBinding.root)
        initNavigation()
        navigatorViewModel.navigateTo(NavDestination(R.id.searchFragment))
    }

    private fun initNavigation() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.mainContainer)
        if (currentFragment is NavHostFragment) {
            navController = currentFragment.navController
        }
        navigatorViewModel.fragmentDestination.observe(this) { handleWithCoreNavigator(it) }
    }

    private fun handleWithCoreNavigator(navDestination: NavDestination) {
        try {
            navController.navigate(
                navDestination.destination,
                navDestination.bundle,
                navDestination.navOptions,
                navDestination.navExtras
            )
        } catch (argEx: IllegalArgumentException) {
            Log.e(TAG, "Failed to navigate to desired destination", argEx)
        }
    }

}