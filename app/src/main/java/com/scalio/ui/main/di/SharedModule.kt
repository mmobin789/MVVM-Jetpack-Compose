package com.scalio.ui.main.di

import android.content.Context
import com.scalio.app.Scalio
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object SharedModule {
    @Provides
    fun providesContext(): Context = Scalio.instance
}
