package com.spacephotos

import com.sp.common.CoroutineContextProvider
import org.koin.dsl.module

val AppModule = module {
    factory { CoroutineContextProvider() }
    single { com.sp.network.SpaceImageService.getInstance() }
}