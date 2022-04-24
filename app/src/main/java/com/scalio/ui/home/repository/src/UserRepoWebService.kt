package com.scalio.ui.home.repository.src

import com.scalio.ui.search.model.remote.Users
import retrofit2.http.GET
import retrofit2.http.Url

interface UserRepoWebService {

    @GET
    suspend fun searchUser(@Url url: String): Users
}
