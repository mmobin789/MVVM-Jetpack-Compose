package com.scalio.ui.search

import com.scalio.ui.home.repository.UserRepository
import com.scalio.ui.search.model.remote.Users

class SearchViewLogic(private val userRepository: UserRepository) {

    private var pageNo = 1

    suspend fun searchUser(user: String, pageNo: Int, perPage: Int): Users {
        return userRepository.searchUser(user, pageNo, perPage)
    }
}
