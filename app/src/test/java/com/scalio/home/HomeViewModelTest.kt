package com.scalio.home

import com.scalio.ui.home.HomeViewModel
import com.scalio.ui.home.HomeViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.util.*

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    private val classUnderTest = HomeViewModel()

    private val testCoroutineScope = TestScope()

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @Test
    fun `When searched user is blank then return input fail`() {

        classUnderTest.searchUsers(" ")

        Assert.assertTrue(classUnderTest(testCoroutineScope) is HomeViewState.InputFail)
    }

    @Test
    fun `When searched a valid user then return success`() {

        classUnderTest.searchUsers(UUID.randomUUID().toString())

        Assert.assertTrue(classUnderTest(testCoroutineScope) is HomeViewState.Success)
    }

    @Test
    fun `When home view state loads then return idle`() {
        Assert.assertTrue(classUnderTest(testCoroutineScope) is HomeViewState.Idle)
    }

    @Test
    fun `When home view state reset then return idle`() {

        classUnderTest.reset()

        Assert.assertTrue(classUnderTest(testCoroutineScope) is HomeViewState.Idle)
    }
}
