package com.scalio.ui.home.repository.src

class UserRepoRemoteSourceImpl(private val userRepoWebService: UserRepoWebService) :
    UserRepoRemoteSource {
    override suspend fun searchUser(user: String, pageNo: Int, perPage: Int) =
        userRepoWebService.searchUser("https://api.github.com/search/users?q=$user&page=$pageNo&per_page=$perPage in:login")
}
