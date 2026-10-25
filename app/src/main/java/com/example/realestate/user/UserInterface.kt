package com.example.realestate.user

import retrofit2.Response
import retrofit2.http.GET

interface UserInterface
{

    @GET("broker/get-all-brokers")
    suspend fun getAllBrokers(): Response<BrokerResponse>


}