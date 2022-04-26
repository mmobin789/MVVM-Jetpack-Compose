package com.scalio.ui.main.di

import android.content.Context
import com.google.gson.Gson
import com.scalio.app.Scalio
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object SharedModule {
    @Provides
    fun providesContext(): Context = Scalio.instance

    @Singleton
    @Provides
    fun providesGson() = Gson()
}
