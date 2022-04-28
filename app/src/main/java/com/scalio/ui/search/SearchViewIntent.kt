package com.scalio.ui.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchViewIntent(private val searchViewLogic: SearchViewLogic) {

    private lateinit var composeScope: CoroutineScope

    private val uiState = mutableStateOf<SearchViewState>(SearchViewState.Idle)

    operator fun invoke(composeScope: CoroutineScope): State<SearchViewState> {
        this.composeScope = composeScope
        return uiState
    }

    fun reset() {
        uiState.value = SearchViewState.Idle
    }

    fun searchUsers(user: String) {
        uiState.value = SearchViewState.Loading

        composeScope.launch {
            uiState.value = try {
                SearchViewState.Success(
                    withContext(Dispatchers.IO) {
                        searchViewLogic.searchUser(user)
                    }
                )
            } catch (e: Exception) {
                SearchViewState.Fail(e.message)
            }
        }
    }
}
