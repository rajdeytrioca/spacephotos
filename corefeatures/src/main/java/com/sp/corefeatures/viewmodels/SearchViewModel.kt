package com.sp.corefeatures.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sp.common.CoroutineContextProvider
import com.sp.common.entities.SearchItem
import com.sp.common.entities.SearchResponse
import com.sp.corefeatures.ImagePagingSource
import com.sp.network.SpaceImageService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.lang.Exception

class SearchViewModel(
    private val apiRepo: SpaceImageService,
) : ViewModel() {

    companion object{
        private const val TAG = "SearchViewModel"
    }

    private val _fetched : MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val fetched:LiveData<Boolean> = _fetched
    var imagePager: Flow<PagingData<SearchItem>>?=null

    fun submitSearch(query: String) {
       imagePager = Pager(
            PagingConfig(pageSize = 20)
        ) {
            ImagePagingSource(apiRepo, query)
        }.flow
            .cachedIn(viewModelScope)
    }
}