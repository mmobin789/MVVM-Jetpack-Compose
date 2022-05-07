package com.scalio.search.repository

import com.scalio.ui.search.repository.UserRepository
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import java.util.*
import kotlin.random.Random

class UserRepositoryTest {

    private val classUnderTest: UserRepository = TestUserRepoImpl

    @Test
    fun `When get users method is invoked then return user listing paged data flow`() {

        val user = UUID.randomUUID().toString()

        val page = Random.nextInt(10)

        val perPage = Random.nextInt(10)

        val data = runBlocking { classUnderTest.getUsers(user, page, perPage).toList() }

        Assert.assertTrue(data.isNotEmpty())
    }
}
