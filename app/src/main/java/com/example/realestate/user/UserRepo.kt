package com.example.realestate.user

import com.example.realestate.ApiClient
import retrofit2.Response

class UserRepo {

    val api= ApiClient.userApi

    suspend fun getBrokers(): Response<BrokerResponse>{
        return api.getAllBrokers()
    }

}