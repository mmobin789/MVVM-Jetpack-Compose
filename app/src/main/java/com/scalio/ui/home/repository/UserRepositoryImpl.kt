package com.scalio.ui.home.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.scalio.ui.home.repository.src.UserRepoRemoteSource
import com.scalio.ui.search.model.remote.UserRemotePagingSource

class UserRepositoryImpl(private val userRepoRemoteSource: UserRepoRemoteSource) : UserRepository {
    override fun getUsers(user: String, page: Int, perPage: Int) =
        Pager(PagingConfig(pageSize = 9)) {
            UserRemotePagingSource(user, userRepoRemoteSource)
        }.flow
}
