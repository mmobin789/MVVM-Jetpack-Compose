package com.scalio.ui.search.model.remote

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Users(
    @SerializedName("total_count") val totalCount: Int,
    @SerializedName("items") val users: List<User>,
    var query: String = ""
) : Parcelable
