package com.example.realestate.user

import com.example.realestate.ApiClient
import retrofit2.Response

class UserRepo {

    val api= ApiClient.userApi

    val token=""

    suspend fun getBrokers(): Response<AllBrokerResponse>{
        return api.getAllBrokers()
    }

    suspend fun getBrokerProfile(id:String): Response<BrokerProfileResponse>{
        return api.getBrokerProfile(id)
    }

    suspend fun getBrokerAvailability(id:String): Response<Availability>{
        return api.getBrokerAvailability(id)
    }

    suspend fun createBrokerAppointment(request: AppointmentRequest): Response<AppointmentResponse>{
        return api.createBrokerAppointment(
            "Bearer ${token}",
            request = request
        )
    }
}