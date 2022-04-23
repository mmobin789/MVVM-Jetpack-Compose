package com.scalio.ui.home.repository.src

import com.scalio.ui.search.model.remote.Users

class UserRepoRemoteSourceImpl(private val userRepoWebService: UserRepoWebService) :
    UserRepoRemoteSource {
    override suspend fun searchUser(user: String, pageNo: Int, perPage: Int): Users {
       // val query = "q=daanidev&page=$pageNo&per_page=$perPage in:login"
        return userRepoWebService.searchUser("daanidev")
    }
}
