package com.spacephotos

import com.sp.corefeatures.viewmodels.NavigatorViewModel
import com.sp.corefeatures.viewmodels.SearchViewModel
import com.spacephotos.viewmodels.LauncherViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@FlowPreview
@ExperimentalCoroutinesApi
val ViewModelProvidersModule = module {
    viewModel {
        LauncherViewModel(
            coroutineContextProvider = get(),
        )
    }

    viewModel { NavigatorViewModel() }
    viewModel { SearchViewModel(get()) }
}