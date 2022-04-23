package com.scalio.ui.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.scalio.R
import kotlinx.coroutines.CoroutineScope

class HomeViewIntent {

    private lateinit var composeScope: CoroutineScope

    private val uiState = mutableStateOf<HomeViewState>(HomeViewState.Idle)

    operator fun invoke(composeScope: CoroutineScope): State<HomeViewState> {
        this.composeScope = composeScope
        return uiState
    }

    fun reset() {
        uiState.value = HomeViewState.Idle
    }

    fun loadSearchedUsers(user: String) {
        uiState.value = if (user.isBlank()) {
            HomeViewState.Fail(R.string.txt_blank)
        } else
            HomeViewState.Loading
    }
}
