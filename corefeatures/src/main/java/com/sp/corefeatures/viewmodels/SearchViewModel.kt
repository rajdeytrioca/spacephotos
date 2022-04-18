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
    private val coroutineContextProvider: CoroutineContextProvider
) : ViewModel() {

    companion object{
        private const val TAG = "SearchViewModel"
    }

    private val _fetched : MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    private lateinit var result : SearchResponse
    val fetched:LiveData<Boolean> = _fetched
    var imagePager: Flow<PagingData<SearchItem>>?=null



    fun submitSearch(query: String) {
       imagePager = Pager(
            // Configure how data is loaded by passing additional properties to
            // PagingConfig, such as prefetchDistance.
            PagingConfig(pageSize = 100)
        ) {
            ImagePagingSource(apiRepo, query)
        }.flow
            .cachedIn(viewModelScope)

    /* viewModelScope.launch(coroutineContextProvider.io) {
            try {
                result = apiRepo.getImages(query)
                _fetched.postValue(true)
            }catch (e:Exception){
                Log.d(TAG, "submitSearch: ")
            }
        }*/
    }
}