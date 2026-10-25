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
class MLVm ():ViewModel() {

    val _housePriceState = MutableStateFlow<HousePriceState>(HousePriceState.Empty)
    val housePriceState: StateFlow<HousePriceState> = _housePriceState.asStateFlow()

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


}