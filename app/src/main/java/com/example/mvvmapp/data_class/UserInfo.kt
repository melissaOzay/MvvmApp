package com.example.mvvmapp.data_class

import com.google.gson.annotations.SerializedName

data class UserInfo(
    @SerializedName("id") var id: String?,
    @SerializedName("name") var name: String?,
    @SerializedName("surname") var surname: String?,
    @SerializedName("email") var email: String?,
    @SerializedName("password") var password: String?
)
