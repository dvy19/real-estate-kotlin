package com.example.realestate.ml

import com.example.realestate.ApiClient
import retrofit2.Response

class MLRepo {


    suspend fun predictHousePrice(req: PricePredictReq): Response<PredictPriceResponse>{


        return ApiClient.mlApi.predictPrice(req)
    }

    val token="abcd"

    suspend fun sendRagMsg(request:RagMsgReq):Response<RagChatResponse>{

        return ApiClient.mlApi.sendRagMsg(
            "Bearer ${token}",
            request
        )
    }
}