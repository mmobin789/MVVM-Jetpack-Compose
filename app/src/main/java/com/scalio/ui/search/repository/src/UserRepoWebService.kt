package com.scalio.ui.search.repository.src

import com.scalio.ui.search.model.remote.GithubUsers
import retrofit2.http.GET
import retrofit2.http.Query

interface UserRepoWebService {

    @GET("search/users")
    suspend fun searchUser(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): GithubUsers
}
