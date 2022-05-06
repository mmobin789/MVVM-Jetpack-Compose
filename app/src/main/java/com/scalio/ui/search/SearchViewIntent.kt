package com.scalio.ui.search

import androidx.compose.runtime.mutableStateOf
import androidx.paging.cachedIn
import kotlinx.coroutines.CoroutineScope

class SearchViewIntent(private val searchViewLogic: SearchViewLogic) {

    private lateinit var composeScope: CoroutineScope

    private val uiState = mutableStateOf<SearchViewState>(SearchViewState.Loading)

    operator fun invoke(composeScope: CoroutineScope): SearchViewState {
        this.composeScope = composeScope
        return uiState.value
    }

    fun searchUsers(user: String) {
        uiState.value = SearchViewState.UserListing(searchViewLogic.searchUser(user).cachedIn(composeScope))
    }
}
