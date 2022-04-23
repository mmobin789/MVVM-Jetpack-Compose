package com.scalio.ui.home.repository

import com.scalio.ui.home.repository.src.UserRepoRemoteSource

class UserRepositoryImpl(private val userRepoRemoteSource: UserRepoRemoteSource) : UserRepository {
    override suspend fun searchUser(user: String, pageNo: Int, perPage: Int) =
        userRepoRemoteSource.searchUser(user, pageNo, perPage)
}
