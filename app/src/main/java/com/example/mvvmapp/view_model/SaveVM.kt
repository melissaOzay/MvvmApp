package com.example.mvvmapp.view_model


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmapp.api.RestApi
import com.example.mvvmapp.data_class.UserInfo
import com.example.mvvmapp.service.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SaveVM : ViewModel() {

    var createNewUserLiveData: MutableLiveData<List<UserInfo>> = MutableLiveData()
    val failure = MutableLiveData<String>()


    fun createNewUser(userData: UserInfo) {
        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        retrofit.addUser(userData).enqueue(object : Callback<List<UserInfo>> {
            override fun onResponse(
                call: Call<List<UserInfo>>,
                response: Response<List<UserInfo>>
            ) {
                if (response.isSuccessful) {
                    createNewUserLiveData.postValue(response.body())
                } else {
                    failure.postValue(response.errorBody().toString())
                }
            }
            override fun onFailure(call: Call<List<UserInfo>>, t: Throwable) {
                failure.postValue(t.message)
            }
        })
    }
}