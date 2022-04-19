package com.sp.corefeatures.fragments

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sp.corefeatures.ImageAdapter
import com.sp.corefeatures.ImageDiffCallback
import com.sp.corefeatures.NavDestination
import com.sp.corefeatures.R
import com.sp.corefeatures.viewmodels.NavigatorViewModel
import com.sp.corefeatures.viewmodels.SearchViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchResultFragment : Fragment(R.layout.search_results) {

    companion object {
        private const val TAG = "SearchResultFragment"
    }

    private val navigatorViewModel: NavigatorViewModel by sharedViewModel()
    private val searchViewmodel: SearchViewModel by viewModel()
    private lateinit var views: Views
    private lateinit var adapter: ImageAdapter


    private class Views {
        lateinit var gridView: RecyclerView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        views = Views().also {
            it.gridView = view.findViewById(R.id.gvImages)
        }

        setupGrid()

        val searchTerm = arguments?.getString("searchterm")
        attachPagerWithSearch(searchTerm)
    }

    private fun attachPagerWithSearch(searchTerm: String?) {
        searchTerm?.let {
            searchViewmodel.submitSearch(it)
            lifecycleScope.launch {
                searchViewmodel.imagePager?.collectLatest { pagingData ->
                    adapter.submitData(pagingData)
                }
            }
        }
    }

    private fun setupGrid() {
        val dividerItemDecoration = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        views.gridView.addItemDecoration(dividerItemDecoration)
        views.gridView.layoutManager = LinearLayoutManager(context)
        adapter = ImageAdapter(ImageDiffCallback()) {
            val data = it?.data?.get(0)
            val link = it?.links?.get(0)

            data?.let {
                val bundle = bundleOf(
                    "link" to link?.href,
                    "title" to data.title,
                    "desc" to data.description
                )
                navigatorViewModel.navigateTo(NavDestination(R.id.detailsFragment,bundle))
            }
        }
        views.gridView.adapter = adapter
    }

}