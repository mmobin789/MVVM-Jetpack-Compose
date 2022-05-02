package com.scalio.ui.home.di

import com.scalio.ui.home.HomeViewIntent
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object SourceModule {
/*    @Provides
    @Reusable
    fun providesHomeViewLogic(userRepository: UserRepository) = HomeViewLogic(userRepository)*/

    @Provides
    @Reusable
    fun providesHomeViewIntent() = HomeViewIntent()
}
