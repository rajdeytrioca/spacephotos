package com.sp.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@ExperimentalCoroutinesApi
class CoroutineTestRule : TestWatcher() {

    /**
     * A TestCoroutineDispatcher to run coroutines against
     */
    val dispatcher = StandardTestDispatcher()

    /**
     * A TestCoroutineScope used for compositing coroutines under test
     */
    val scope = TestScope(dispatcher)

    /**
     * A CoroutineContextProvider which can be given to code under test which
     * links Dispatchers to the TestDispatcher.
     */
    val contextProvider = TestCoroutineContextProvider(dispatcher)

    override fun starting(description: Description?) {
        super.starting(description)

        Dispatchers.setMain(dispatcher)
    }

    override fun finished(description: Description?) {
        Dispatchers.resetMain()
        super.finished(description)
    }
}