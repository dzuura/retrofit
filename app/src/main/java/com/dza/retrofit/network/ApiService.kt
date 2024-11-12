package com.dza.retrofit.network

import com.dza.retrofit.model.Users
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
        @GET("users?page=2")
        fun getAllUsers(): Call<Users>
}