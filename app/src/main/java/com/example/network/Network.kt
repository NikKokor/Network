package com.example.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Network {
    @GET("users/{user}")
    fun getUser(@Path("user") user: String?): Call<UserInfo>
}