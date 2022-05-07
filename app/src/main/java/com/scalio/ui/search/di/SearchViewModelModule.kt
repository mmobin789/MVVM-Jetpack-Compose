package com.scalio.ui.search.di

import com.scalio.ui.search.SearchViewLogic
import com.scalio.ui.search.repository.UserRepository
import com.scalio.ui.search.repository.UserRepositoryImpl
import com.scalio.ui.search.repository.src.UserRepoRemoteSource
import com.scalio.ui.search.repository.src.UserRepoRemoteSourceImpl
import com.scalio.ui.search.repository.src.UserRepoWebService
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object SearchViewModelModule {
    @Provides
    @Reusable
    fun providesSearchViewLogic(userRepository: UserRepository) = SearchViewLogic(userRepository)

    @Provides
    @Reusable
    fun providesUserRepoRemoteSource(userRepoWebService: UserRepoWebService): UserRepoRemoteSource =
        UserRepoRemoteSourceImpl(userRepoWebService)

    @Provides
    @Reusable
    fun providesUserRepository(userRepoRemoteSource: UserRepoRemoteSource): UserRepository =
        UserRepositoryImpl(userRepoRemoteSource)

    @Provides
    @Reusable
    fun provideUserRepoWebService(retrofit: Retrofit) =
        retrofit.create(UserRepoWebService::class.java)
}
