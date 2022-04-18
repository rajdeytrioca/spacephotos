package com.sp.corefeatures.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.sp.corefeatures.NavDestination
import com.sp.corefeatures.R
import com.sp.corefeatures.viewmodels.NavigatorViewModel
import com.sp.corefeatures.viewmodels.SearchViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment(R.layout.search_fragment) {

    private val navigatorViewModel: NavigatorViewModel by sharedViewModel()
    private val searchViewmodel:SearchViewModel by viewModel()
    private var views: Views? = null

    private class Views {
        lateinit var searchButton : Button
        lateinit var searchTerm: EditText
        lateinit var progressBar : ProgressBar
    }

    override fun onResume() {
        super.onResume()
        views?.progressBar?.isVisible = false
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        views = Views().also {
            it.searchButton = view.findViewById(R.id.btnSearch)
            it.searchTerm = view.findViewById(R.id.searchTerm)
            it.progressBar = view.findViewById(R.id.progress)
        }

        views?.searchButton?.setOnClickListener {
            val searchTerm = views?.searchTerm?.text.toString()
            if(searchTerm.isEmpty()){
                Toast.makeText(context,"Search Term needed",Toast.LENGTH_LONG).show()
            }
            else {
                views?.progressBar?.isVisible = true
                val bundle = bundleOf("searchterm" to searchTerm)
                navigatorViewModel.navigateTo(NavDestination(R.id.searchResultFragment,bundle))
            }
        }

        searchViewmodel.fetched.observe(viewLifecycleOwner,{
            views?.progressBar?.isVisible = false

        })
    }

    override fun onDestroyView() {
        views = null
        super.onDestroyView()
    }
}