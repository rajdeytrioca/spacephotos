package com.sp.network

import android.os.Build
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/*@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])*/
@ExperimentalCoroutinesApi
class ApiTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Test
    fun addition_isCorrect() {
        val spaceImageService = SpaceImageService.getInstance()
        coroutineTestRule.scope.runTest {
            val images = spaceImageService.getImages("mars",1)
            assertEquals(100, images.collection.items.size)

        }
    }
}