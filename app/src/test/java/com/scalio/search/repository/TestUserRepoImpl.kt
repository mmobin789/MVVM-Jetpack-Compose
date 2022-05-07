package com.scalio.search.repository

import androidx.paging.PagingData
import com.scalio.ui.search.model.remote.GithubUser
import com.scalio.ui.search.repository.UserRepository
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object TestUserRepoImpl : UserRepository {
    override fun getUsers(user: String, page: Int, perPage: Int): Flow<PagingData<GithubUser>> {
        return flow {
            emit(PagingData.from(listOf(mockk())))
        }
    }
}
