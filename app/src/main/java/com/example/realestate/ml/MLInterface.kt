package com.example.realestate.ml

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST


interface MLInterface{

    @POST("ml/predict-house-price")
    suspend fun predictPrice(
        @Body request: PricePredictReq
    ) : Response<PredictPriceResponse>

    @POST("ml/rag-chat")
    suspend fun sendRagMsg(
        @Header("Authorization") token:String,
        @Body request: RagMsgReq
        ) : Response<RagChatResponse>


}