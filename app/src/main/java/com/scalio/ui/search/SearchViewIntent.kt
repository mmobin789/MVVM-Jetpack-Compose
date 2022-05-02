package com.scalio.ui.search

import androidx.paging.cachedIn
import kotlinx.coroutines.CoroutineScope

class SearchViewIntent(private val searchViewLogic: SearchViewLogic) {

    fun searchUsers(user: String, coroutineScope: CoroutineScope) =
        searchViewLogic.searchUser(user).cachedIn(coroutineScope)
}
