package com.spacephotos

import android.app.Application
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
@ExperimentalCoroutinesApi
@FlowPreview
class SpacePhotosApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initkoin()
    }

    private fun initkoin() {
        startKoin {
            androidContext(this@SpacePhotosApplication)
            modules(
                AppModule,
                ViewModelProvidersModule
            )
        }
    }
}