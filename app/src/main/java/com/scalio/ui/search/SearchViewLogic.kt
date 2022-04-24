package com.scalio.ui.search

import com.scalio.ui.home.repository.UserRepository
import com.scalio.ui.search.model.remote.Users

class SearchViewLogic(private val userRepository: UserRepository) {

    private var page = 1

    suspend fun searchUser(user: String, pageNo: Int, perPage: Int): Users {

        val users = userRepository.searchUser(user, page, perPage)

        page = if (pageNo > 1)
            pageNo
        else page++

        return users
    }
}
