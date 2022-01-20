package com.example.network

import com.google.gson.annotations.SerializedName

data class UserInfo (
    @SerializedName("login") val login: String?,
    @SerializedName("avatar_url") val avatarUrl: String?,
    @SerializedName("html_url") val url: String?,
    @SerializedName("public_repos") val publicRepos: String?
)
