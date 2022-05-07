package com.scalio.ui.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.scalio.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

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

        uiState.value = if (user.isBlank()) {
            HomeViewState.InputFail(R.string.txt_blank)
        } else {
            HomeViewState.Success
        }
    }
}
