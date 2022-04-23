package com.scalio.ui.search.model.remote

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("avatar_url")
    val avatarUrl: String,
    val login: String,
    val type: String
)
