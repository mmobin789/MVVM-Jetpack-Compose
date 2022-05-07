package com.scalio.ui.search.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.scalio.ui.search.model.remote.UserRemotePagingSource
import com.scalio.ui.search.repository.src.UserRepoRemoteSource

class UserRepositoryImpl(private val userRepoRemoteSource: UserRepoRemoteSource) : UserRepository {
    override fun getUsers(user: String, page: Int, perPage: Int) =
        Pager(PagingConfig(pageSize = perPage)) {
            UserRemotePagingSource(user, userRepoRemoteSource)
        }.flow
}
