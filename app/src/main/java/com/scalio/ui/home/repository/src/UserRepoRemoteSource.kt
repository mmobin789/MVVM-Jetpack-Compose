package com.scalio.ui.home.repository.src

import com.scalio.ui.search.model.remote.GithubUsers

interface UserRepoRemoteSource {
    suspend fun getUsers(user: String, page: Int, perPage: Int): GithubUsers
    suspend fun getUsers1stPage(user: String, perPage: Int): GithubUsers
}
