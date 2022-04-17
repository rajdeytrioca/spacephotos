package com.sp.common

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

open class CoroutineContextProvider {

    /**
     * The Default Coroutine Dispatcher
     */
    open val default: CoroutineContext by lazy { Dispatchers.Default }

    /**
     * The Main Coroutine Dispatcher
     */
    open val main: CoroutineContext by lazy { Dispatchers.Main }

    /**
     * The IO Coroutine Dispatcher
     */
    open val io: CoroutineContext by lazy { Dispatchers.IO }

    /**
     * The Unconfined Coroutine Dispatcher
     */
    open val unconfined: CoroutineContext by lazy { Dispatchers.Unconfined }
}