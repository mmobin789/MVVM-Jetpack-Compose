package com.scalio.search

import com.scalio.ui.search.SearchViewLogic
import com.scalio.ui.search.SearchViewModel
import com.scalio.ui.search.SearchViewState
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.util.*

@OptIn(ExperimentalCoroutinesApi::class)
class SearchViewModelTest {

    private val searchViewLogic = mockk<SearchViewLogic>()

    private val classUnderTest = SearchViewModel(searchViewLogic)

    private val testCoroutineScope = TestScope()

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @Test
    fun `When searched users are loading then return loading state`() {

        Assert.assertTrue(classUnderTest(testCoroutineScope) == SearchViewState.Loading)
    }

    @Test
    fun `When searched users are found then return user listing state`() {

        val user = UUID.randomUUID().toString()

        every { searchViewLogic.searchUser(user) } returns mockk()

        classUnderTest.searchUsers(user)

        val searchViewState = classUnderTest(testCoroutineScope)

        Assert.assertTrue(searchViewState is SearchViewState.UserListing)
    }
}
