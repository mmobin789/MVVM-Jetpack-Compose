package com.scalio

import android.util.Log

object AppLogger {
    private var loggingEnabled = false

    fun enabled(loggingEnabled: Boolean) {
        AppLogger.loggingEnabled = loggingEnabled
    }

    fun Any.d(message: String?): String {
        if (loggingEnabled)
            Log.d(javaClass.simpleName, "$message")

        return message.orEmpty()
    }

    fun Any.e(message: String?): String {
        if (loggingEnabled)
            Log.e(javaClass.simpleName, "$message")

        return message.orEmpty()
    }
}
