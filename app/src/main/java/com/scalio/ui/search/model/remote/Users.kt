package com.scalio.ui.search.model.remote

import com.google.gson.annotations.SerializedName

data class Users(
    @SerializedName("total_count") val totalCount: Int,
    @SerializedName("items") val users: List<User>
)
