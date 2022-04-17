package com.sp.network

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@ExperimentalCoroutinesApi
class ExampleUnitTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Test
    fun addition_isCorrect() {
        val spaceImageService = SpaceImageService.getInstance()
        coroutineTestRule.scope.runTest {
            val images = spaceImageService.getImages("mars")
            assertEquals(1, images.body()?.size)

        }
    }
}