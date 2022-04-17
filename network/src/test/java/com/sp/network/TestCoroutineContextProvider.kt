package com.sp.network

import com.sp.common.CoroutineContextProvider
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class TestCoroutineContextProvider(coroutineContext : CoroutineContext = Dispatchers.Unconfined)
    : CoroutineContextProvider() {

    override val default = coroutineContext
    override val main = coroutineContext
    override val io = coroutineContext
    override val unconfined = coroutineContext

}
