package com.scalio.ui.search.model.remote

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubUser(
    @SerializedName("avatar_url")
    val avatarUrl: String,
    val login: String,
    val type: String
) : Parcelable
