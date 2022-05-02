package com.scalio.ui.search

import com.scalio.ui.home.repository.UserRepository

class SearchViewLogic(private val userRepository: UserRepository) {

    fun searchUser(user: String) = userRepository.getUsers(user, 1, 9)
}
