package com.scalio.ui.home.repository

import com.scalio.ui.search.model.remote.Users

interface UserRepository {
    suspend fun searchUser(user: String, pageNo: Int, perPage: Int): Users
}
