package com.example.realestate.user

import com.example.realestate.ApiClient
import retrofit2.Response

class UserRepo {

    val api= ApiClient.userApi

    suspend fun getBrokers(): Response<BrokerResponse>{
        return api.getAllBrokers()
    }

    suspend fun getBrokerProfile(id:String): Response<BrokerResponse>{
        return api.getBrokerProfile(id)
    }

    suspend fun getBrokerAvailability(id:String): Response<Availability>{
        return api.getBrokerAvailability(id)
    }
}