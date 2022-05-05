package com.scalio.ui.search.model.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.scalio.ui.home.repository.src.UserRepoRemoteSource

class UserRemotePagingSource(
    private val user: String,
    private val userRepoRemoteSource: UserRepoRemoteSource
) :
    PagingSource<Int, GithubUser>() {

    override fun getRefreshKey(state: PagingState<Int, GithubUser>) = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GithubUser> {
        val nextPage = params.key ?: 1
        return try {
            userRepoRemoteSource.getUsers(user, nextPage, params.loadSize).githubUsers.run {
                LoadResult.Page(
                    data = this,
                    if (nextPage == 1) null else nextPage - 1,
                    if (isEmpty()) null else nextPage + 1
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
