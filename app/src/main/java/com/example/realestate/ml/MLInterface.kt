package com.example.realestate.ml

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface MLInterface{

    @POST("ml/predict-price")
    suspend fun predictPrice(
        @Body request: PricePredictReq
    ) : Response<PredictPriceResponse>

}