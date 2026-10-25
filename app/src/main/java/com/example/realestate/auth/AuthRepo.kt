package com.example.realestate.auth

import com.example.realestate.ApiClient
import retrofit2.Response

class AuthRepo {

    val api= ApiClient.authApi

    suspend fun registerUser(req: SignupRequest):Response<SignupRes>{
        return api.register(req)
    }

    suspend fun loginUser(req: LoginReq):Response<SignupRes>{
        return api.login(req)
    }


}