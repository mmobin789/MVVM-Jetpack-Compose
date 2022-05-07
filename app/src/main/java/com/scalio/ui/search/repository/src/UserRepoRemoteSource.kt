package com.scalio.ui.search.repository.src

import com.scalio.ui.search.model.remote.GithubUsers

interface UserRepoRemoteSource {
    suspend fun getUsers(user: String, page: Int, perPage: Int): GithubUsers
}
