package com.scalio.ui.search

import androidx.paging.PagingData
import com.scalio.ui.search.model.remote.GithubUser
import kotlinx.coroutines.flow.Flow

sealed class SearchViewState {
    object Loading : SearchViewState()
    class UserListing(val flowPagingData: Flow<PagingData<GithubUser>>) : SearchViewState()
}
