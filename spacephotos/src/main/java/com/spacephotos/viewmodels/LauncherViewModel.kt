package com.spacephotos.viewmodels

import android.text.BoringLayout
import androidx.lifecycle.ViewModel
import com.sp.common.CoroutineContextProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@ExperimentalCoroutinesApi
@FlowPreview
internal class LauncherViewModel(
    private val coroutineContextProvider: CoroutineContextProvider,
) : ViewModel() {

}

