package com.scalio.ui.home

import androidx.compose.runtime.mutableStateOf
import com.scalio.R
import kotlinx.coroutines.CoroutineScope

class HomeViewIntent {

    private lateinit var composeScope: CoroutineScope

    private val uiState = mutableStateOf<HomeViewState>(HomeViewState.Idle)

    operator fun invoke(composeScope: CoroutineScope): HomeViewState {
        this.composeScope = composeScope
        return uiState.value
    }

    fun reset() {
        uiState.value = HomeViewState.Idle
    }

    fun searchUsers(user: String) {

        if (user.isBlank()) {
            uiState.value = HomeViewState.InputFail(R.string.txt_blank)
            return
        } else {
            uiState.value = HomeViewState.Success
        }
    }
}
