package com.scalio.ui.home

import androidx.annotation.StringRes

sealed class HomeViewState {
    object Idle : HomeViewState()
    class InputFail(@StringRes val error: Int) : HomeViewState()
    object Success : HomeViewState()
}
