package com.scalio.app

import android.app.Application
import com.scalio.AppLogger
import com.scalio.BuildConfig
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Scalio : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        AppLogger.enabled(BuildConfig.DEBUG)
    }

    companion object {
        lateinit var instance: Scalio
            private set
    }
}
