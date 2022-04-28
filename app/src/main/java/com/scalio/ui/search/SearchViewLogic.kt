package com.scalio.ui.search

import com.scalio.ui.home.repository.UserRepository
import com.scalio.ui.search.model.remote.Users

class SearchViewLogic(private val userRepository: UserRepository) {

    private var page = 2

    suspend fun searchUser(user: String): Users {
        val users = userRepository.searchUser(user, page, 9)
        if (users.users.isNotEmpty())
            page++
        return users
    }
}
