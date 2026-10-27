package com.example.realestate.user

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface UserInterface
{

    @GET("broker/get-all-brokers")
    suspend fun getAllBrokers(): Response<AllBrokerResponse>

    suspend fun getBrokerAvailability(
        @Path("id") brokerId:String
    ) : Response<Availability>

    suspend fun getBrokerProfile(
        @Path("id") id:String
    ) : Response<BrokerProfileResponse>

    suspend fun createBrokerAppointment(
        @Header("Authorization") token: String,
        @Body request: AppointmentRequest
    ) : Response<AppointmentResponse>


}