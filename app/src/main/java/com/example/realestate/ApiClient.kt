package com.example.realestate

import com.example.realestate.auth.AuthInterface
import com.example.realestate.ml.MLInterface
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object ApiClient {

    private const val BASE_URL = "https://waste-management-hiay.onrender.com/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()
/*
The Lazy delegate supports getValue(), but it doesn't support setValue().

Therefore, it works with val but not var.

 */
    val authApi: AuthInterface by lazy{
        retrofit.create(AuthInterface::class.java)
    }

    val mlApi: MLInterface by lazy{
        retrofit.create(MLInterface::class.java)

    }

}