package com.example.realestate.ml

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


sealed class HousePriceState{
    object Empty: HousePriceState()
    object Loading: HousePriceState()
    class Success(val data: PredictPriceResponse): HousePriceState()
    class Error(val message: String): HousePriceState()
}

sealed class RagChatReq{
    object Empty: RagChatReq()
    object Loading: RagChatReq()
    class Success(val data: RagChatResponse): RagChatReq()
    class Error(val message: String): RagChatReq()
}

class MLVm ():ViewModel() {

    val _housePriceState = MutableStateFlow<HousePriceState>(HousePriceState.Empty)
    val housePriceState: StateFlow<HousePriceState> = _housePriceState.asStateFlow()

    val _ragChatState = MutableStateFlow<RagChatReq>(RagChatReq.Empty)
    val ragChatState: StateFlow<RagChatReq> = _ragChatState.asStateFlow()


    val repo=MLRepo()

    fun predictHousePrice(req: PricePredictReq){

        _housePriceState.value = HousePriceState.Loading

        viewModelScope.launch {

           try{
               val response = repo.predictHousePrice(req)

               if(response.isSuccessful && response.body()!=null){
                   _housePriceState.value = HousePriceState.Success(response.body()!!)
               }
               else{
                   _housePriceState.value = HousePriceState.Error(response.message())
               }
           }
           catch (e:Exception){
               _housePriceState.value = HousePriceState.Error(e.message.toString())
           }

        }



    }

    fun sendRagMsg(req: RagMsgReq){

        _ragChatState.value = RagChatReq.Loading

        viewModelScope.launch {

            try{
                val response = repo.sendRagMsg(req)

                if(response.isSuccessful && response.body()!=null){
                    _ragChatState.value = RagChatReq.Success(response.body()!!)
                }

                else{
                    _ragChatState.value = RagChatReq.Error(response.message())
                }
            }
            catch (e:Exception){
                _ragChatState.value = RagChatReq.Error(e.message.toString())
            }

        }
    }


}