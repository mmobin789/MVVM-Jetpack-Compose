package com.scalio.ui.home.repository.src

import com.scalio.ui.search.model.remote.Users

interface UserRepoRemoteSource {
    suspend fun searchUser(user: String, pageNo: Int, perPage: Int): Users
}
