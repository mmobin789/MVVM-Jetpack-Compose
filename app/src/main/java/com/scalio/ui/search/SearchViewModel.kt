package com.scalio.ui.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val searchViewLogic: SearchViewLogic) :
    ViewModel() {

    private var composeScope = viewModelScope

    private val uiState = mutableStateOf<SearchViewState>(SearchViewState.Loading)

    operator fun invoke(composeScope: CoroutineScope): SearchViewState {
        this.composeScope = composeScope
        return uiState.value
    }

    fun searchUsers(user: String) {
        uiState.value =
            SearchViewState.UserListing(searchViewLogic.searchUser(user).cachedIn(composeScope))
    }
}
