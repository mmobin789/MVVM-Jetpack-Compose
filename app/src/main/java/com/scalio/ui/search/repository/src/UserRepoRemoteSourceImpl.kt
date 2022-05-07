package com.scalio.ui.search.repository.src

class UserRepoRemoteSourceImpl(private val userRepoWebService: UserRepoWebService) :
    UserRepoRemoteSource {

    override suspend fun getUsers(user: String, page: Int, perPage: Int) =
        userRepoWebService.searchUser(user, page, perPage)
}
