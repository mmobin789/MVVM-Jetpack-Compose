package com.scalio.ui.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.scalio.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewIntent(private val homeViewLogic: HomeViewLogic) {

    private lateinit var composeScope: CoroutineScope

    private val uiState = mutableStateOf<HomeViewState>(HomeViewState.Idle)

    operator fun invoke(composeScope: CoroutineScope): State<HomeViewState> {
        this.composeScope = composeScope
        return uiState
    }

    fun reset() {
        uiState.value = HomeViewState.Idle
    }

    fun searchUsers(user: String) {

        if (user.isBlank()) {
            uiState.value = HomeViewState.InputFail(R.string.txt_blank)
            return
        } else {
            uiState.value = HomeViewState.Loading
        }

        composeScope.launch {
            uiState.value = try {
                val users = withContext(Dispatchers.IO) {
                    homeViewLogic.searchUser(user)
                }
                HomeViewState.Success(users)
            } catch (e: Exception) {
                HomeViewState.ApiFail(e.message)
            }
        }
    }
}
