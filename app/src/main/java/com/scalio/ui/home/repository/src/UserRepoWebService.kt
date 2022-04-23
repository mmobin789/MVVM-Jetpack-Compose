package com.scalio.ui.home.repository.src

import com.scalio.ui.search.model.remote.Users
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserRepoWebService {
    @GET("search/users?q={user} in:login")
    suspend fun searchUser(@Path("user") query: String): Users

    @GET("search/users")
    suspend fun searchUsers(@Query("q") query: String): Users
}
