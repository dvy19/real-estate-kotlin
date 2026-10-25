package com.example.realestate.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class GetBrokersState{
    object Empty: GetBrokersState()
    object Loading: GetBrokersState()
    class Success(val data: BrokerResponse): GetBrokersState()
    class Error(val message: String): GetBrokersState()
}

class UserVM():ViewModel()
{

    val repo=UserRepo()

    private val _getBrokersState = MutableStateFlow<GetBrokersState>(GetBrokersState.Empty)
    val getBrokersState: StateFlow<GetBrokersState> = _getBrokersState.asStateFlow()

    fun get_brokers(){

        _getBrokersState.value = GetBrokersState.Loading

        viewModelScope.launch {
            try{
                val response = repo.getBrokers()
                if(response.isSuccessful && response.body()!=null){
                    _getBrokersState.value = GetBrokersState.Success(response.body()!!)
                }

                else{
                    _getBrokersState.value = GetBrokersState.Error(response.message())
                }
                }
            catch (e:Exception){
                _getBrokersState.value = GetBrokersState.Error(e.message.toString())
            }
        }
    }



}