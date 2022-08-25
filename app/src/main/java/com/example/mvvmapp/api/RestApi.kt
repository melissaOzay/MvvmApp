package com.example.mvvmapp.api

import com.example.mvvmapp.data_class.UserInfo
import retrofit2.Call
import retrofit2.http.*

interface RestApi {
    @Headers("Content-Type: application/json")
    @POST("post")
    fun addUser(@Body userData: UserInfo): Call<List<UserInfo>>

    @GET("get")
    fun getUser():Call<List<UserInfo>>

    @GET("search")
    fun getMovies(@Query("name") name: String): Call<List<UserInfo>>
}