package com.example.realestate.auth

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthInterface{

    @POST("user/register")
    suspend fun register(
        @Body request: SignupRequest
    ):Response<SignupRes>

    @POST("user/login")
    suspend fun login(
        @Body request: LoginReq
    ):Response<SignupRes>

}