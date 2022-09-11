package com.example.mvvmapp.repository.project_in_repository

import com.example.mvvmapp.data_class.UserInfo

interface UserListInterface {
    fun success(userList: List<UserInfo>)
    fun fail(message: String)
}
