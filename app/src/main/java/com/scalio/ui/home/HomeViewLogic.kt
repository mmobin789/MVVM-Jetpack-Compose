package com.scalio.ui.home

import com.scalio.ui.home.repository.UserRepository

class HomeViewLogic(private val userRepository: UserRepository) {

    suspend fun searchUser(user: String) = userRepository.searchUser(user, 1, 9)
}
