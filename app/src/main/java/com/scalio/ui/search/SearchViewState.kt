package com.scalio.ui.search

import com.scalio.ui.search.model.remote.Users

sealed class SearchViewState {
    object Idle : SearchViewState()
    object Loading : SearchViewState()
    class Fail(val error: String?) : SearchViewState()
    class Success(val data: Users) : SearchViewState()
}
