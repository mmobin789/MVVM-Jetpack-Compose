package com.scalio.ui.home

import androidx.annotation.StringRes
import com.scalio.ui.search.model.remote.Users

sealed class HomeViewState {
    object Idle : HomeViewState()
    object Loading : HomeViewState()
    class Fail(@StringRes val error: Int) : HomeViewState()
    class Success(val users: Users) : HomeViewState()
}
