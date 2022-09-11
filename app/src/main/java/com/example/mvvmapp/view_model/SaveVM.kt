package com.example.mvvmapp.view_model


import androidx.lifecycle.*
import com.example.mvvmapp.data_class.UserInfo
import com.example.mvvmapp.repository.UserRepository
import com.example.mvvmapp.repository.project_in_repository.CreateNewUserInterface

class SaveVM : ViewModel() {

    private var bagRepo = UserRepository()
    var failure = MutableLiveData<String>()
    val userListInfo = MutableLiveData<List<UserInfo>>()

    fun createNewUser(data: UserInfo){
        bagRepo.createNewUser(data,object:CreateNewUserInterface{
            override fun onSuccess(data: List<UserInfo>) {
                userListInfo.postValue(data)
            }

            override fun onFail(message: String) {
                failure.postValue(message)
            }

        }    )
    }

    }









