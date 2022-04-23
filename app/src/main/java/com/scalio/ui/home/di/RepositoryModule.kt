package com.scalio.ui.home.di

import com.scalio.ui.home.repository.UserRepository
import com.scalio.ui.home.repository.UserRepositoryImpl
import com.scalio.ui.home.repository.src.UserRepoRemoteSource
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Reusable
    fun providesUserRepo(userRepoRemoteSource: UserRepoRemoteSource): UserRepository =
        UserRepositoryImpl(userRepoRemoteSource)
}
