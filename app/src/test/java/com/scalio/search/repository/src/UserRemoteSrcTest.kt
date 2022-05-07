package com.scalio.search.repository.src

import com.scalio.ui.search.repository.src.UserRepoRemoteSource
import io.mockk.every
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import java.util.*
import kotlin.random.Random

class UserRemoteSrcTest {

    private val classUnderTest: UserRepoRemoteSource = TestUserRemoteSrcImpl

    @Test
    fun `When get users method is invoked then return github users`() {

        val user = UUID.randomUUID().toString()

        val page = Random.nextInt(10)

        val perPage = Random.nextInt(10)

        val result = runBlocking { classUnderTest.getUsers(user, page, perPage) }

        every { result.githubUsers.isEmpty() } returns false

        val githubUsers = result.githubUsers

        Assert.assertTrue(githubUsers.isNotEmpty())
    }
}
