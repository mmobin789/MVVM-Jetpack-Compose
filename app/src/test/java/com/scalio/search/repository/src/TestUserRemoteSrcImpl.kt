package com.scalio.search.repository.src

import com.scalio.ui.search.model.remote.GithubUsers
import com.scalio.ui.search.repository.src.UserRepoRemoteSource
import io.mockk.mockk

object TestUserRemoteSrcImpl : UserRepoRemoteSource {
    override suspend fun getUsers(user: String, page: Int, perPage: Int): GithubUsers {
        return mockk()
    }
}
