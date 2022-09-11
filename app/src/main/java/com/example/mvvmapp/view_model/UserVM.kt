package com.example.mvvmapp.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmapp.data_class.UserInfo
import com.example.mvvmapp.repository.UserRepository
import com.example.mvvmapp.repository.project_in_repository.SearchInterface
import com.example.mvvmapp.repository.project_in_repository.UserListInterface



class UserVM : ViewModel() {
    private var bagRepo = UserRepository()
    val userListInfo = MutableLiveData<List<UserInfo>>()
    val fail = MutableLiveData<String>()
    val searchList = MutableLiveData<List<UserInfo>>()
    val failure = MutableLiveData<String>()

    fun loadData() {
        bagRepo.loadData(object : UserListInterface {
            override fun success(userList: List<UserInfo>) {
                userListInfo.postValue(userList)
            }

            override fun fail(message: String) {
                fail.postValue(message)
            }

        })
    }

    fun searchAfter(searchAfter: String){
     bagRepo.searchAfter(searchAfter,object :SearchInterface{
         override fun onSuccess(data: List<UserInfo>) {
             searchList.postValue(data)
         }

         override fun onFail(message: String) {
             failure.postValue(message)
         }

     })
    }
}









