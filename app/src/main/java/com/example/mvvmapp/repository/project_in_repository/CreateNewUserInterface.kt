package com.example.mvvmapp.repository.project_in_repository

import com.example.mvvmapp.data_class.UserInfo

interface CreateNewUserInterface {
    fun onSuccess(data: List<UserInfo>)
    fun onFail(message: String)
}