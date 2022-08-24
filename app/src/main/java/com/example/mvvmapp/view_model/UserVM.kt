package com.example.mvvmapp.view_model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmapp.api.RestApi
import com.example.mvvmapp.data_class.UserInfo
import com.example.mvvmapp.service.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserVM : ViewModel() {

    var userList = MutableLiveData<List<UserInfo>>()
    var searchData: MutableLiveData<List<UserInfo>> = MutableLiveData()
    val failure = MutableLiveData<String>()



    fun loadData() {
        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        retrofit.getUser().enqueue(object : Callback<List<UserInfo>> {
            override fun onResponse(
                call: Call<List<UserInfo>>,
                response: Response<List<UserInfo>>
            ) {
                if (response.isSuccessful) {
                    userList.value = response.body()
                }else{
                    failure.postValue(response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<List<UserInfo>>, t: Throwable) {
                failure.postValue(t.message)
            }
        })
    }

    fun searchAfter(searchAfter: String) {
        if (searchAfter.length >= 3){
            val retrofit = ServiceBuilder.buildService(RestApi::class.java)
            retrofit.getMovies(searchAfter).enqueue(object : Callback<List<UserInfo>> {

                override fun onResponse(
                    call: Call<List<UserInfo>>,
                    response: Response<List<UserInfo>>
                ) {
                    if (response.isSuccessful) {
                        searchData.postValue(response.body())

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
}









