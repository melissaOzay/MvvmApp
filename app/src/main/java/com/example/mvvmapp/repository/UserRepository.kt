package com.example.mvvmapp.repository

import com.example.mvvmapp.api.RestApi
import com.example.mvvmapp.data_class.UserInfo
import com.example.mvvmapp.repository.project_in_repository.CreateNewUserInterface
import com.example.mvvmapp.repository.project_in_repository.SearchInterface
import com.example.mvvmapp.repository.project_in_repository.UserListInterface
import com.example.mvvmapp.service.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository {

    fun createNewUser(userData: UserInfo, createNewUserInterface: CreateNewUserInterface) {
        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        retrofit.addUser(userData).enqueue(object : Callback<List<UserInfo>> {
            override fun onResponse(
                call: Call<List<UserInfo>>,
                response: Response<List<UserInfo>>
            ) {
                if (response.isSuccessful) {
                    response.body()
                    createNewUserInterface.onSuccess(response.body()!!)

                } else {
                    createNewUserInterface.onFail(response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<List<UserInfo>>, t: Throwable) {
                createNewUserInterface.onFail(t.message!!)
            }
        })
    }


    fun loadData(userListInterface: UserListInterface) {
        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        retrofit.getUser().enqueue(object : Callback<List<UserInfo>> {
            override fun onResponse(
                call: Call<List<UserInfo>>,
                response: Response<List<UserInfo>>
            ) {
                if (response.isSuccessful) {
                    userListInterface.success(response.body()!!)
              }
            }

            override fun onFailure(call: Call<List<UserInfo>>, t: Throwable) {
                userListInterface.fail(t.localizedMessage!!)
            }
        })
    }

    fun searchAfter(searchAfter: String,searchAfterInterface:SearchInterface)  {
        if (searchAfter.length == 2||searchAfter.length==0){
            val retrofit = ServiceBuilder.buildService(RestApi::class.java)
            retrofit.getMovies(searchAfter).enqueue(object : Callback<List<UserInfo>> {

                override fun onResponse(
                    call: Call<List<UserInfo>>,
                    response: Response<List<UserInfo>>
                ) {
                    if (response.isSuccessful) {
                        searchAfterInterface.onSuccess(response.body()!!)

                    }
                }

                override fun onFailure(call: Call<List<UserInfo>>, t: Throwable) {
                    searchAfterInterface.onFail(t.message!!)

                }
            })
        }
    }
}