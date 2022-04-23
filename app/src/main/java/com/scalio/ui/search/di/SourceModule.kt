package com.scalio.ui.search.di

import com.scalio.ui.home.repository.UserRepository
import com.scalio.ui.home.repository.src.UserRepoRemoteSource
import com.scalio.ui.home.repository.src.UserRepoRemoteSourceImpl
import com.scalio.ui.home.repository.src.UserRepoWebService
import com.scalio.ui.search.SearchViewIntent
import com.scalio.ui.search.SearchViewLogic
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SourceModule {
    @Provides
    @Reusable
    fun providesSearchViewIntent(searchViewLogic: SearchViewLogic) = SearchViewIntent(searchViewLogic)

    @Provides
    @Reusable
    fun providesSearchViewLogic(userRepository: UserRepository) = SearchViewLogic(userRepository)

    @Provides
    @Singleton
    fun providesUserRepoRemoteSource(userRepoWebService: UserRepoWebService): UserRepoRemoteSource =
        UserRepoRemoteSourceImpl(userRepoWebService)
}
