package com.scalio.ui.home.repository

import androidx.paging.PagingData
import com.scalio.ui.search.model.remote.GithubUser
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUsers(user: String, page: Int, perPage: Int): Flow<PagingData<GithubUser>>
}
