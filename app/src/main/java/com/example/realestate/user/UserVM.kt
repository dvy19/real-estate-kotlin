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
    class Success(val data: AllBrokerResponse): GetBrokersState()
    class Error(val message: String): GetBrokersState()
}

sealed class GetBrokerProfileState{
    object Empty: GetBrokerProfileState()
    object Loading: GetBrokerProfileState()
    class Success(val data: BrokerData): GetBrokerProfileState()
    class Error(val message: String): GetBrokerProfileState()

}

sealed class GetBrokerAvailabilityState {
    object Empty : GetBrokerAvailabilityState()
    object Loading : GetBrokerAvailabilityState()
    class Success(val data: Availability) : GetBrokerAvailabilityState()
    class Error(val message: String) : GetBrokerAvailabilityState()
}

class UserVM():ViewModel()
{

    val repo=UserRepo()

    private val _getBrokersState = MutableStateFlow<GetBrokersState>(GetBrokersState.Empty)
    val getBrokersState: StateFlow<GetBrokersState> = _getBrokersState.asStateFlow()

    private val _getBrokerProfileState = MutableStateFlow<GetBrokerProfileState>(GetBrokerProfileState.Empty)
    val getBrokerProfileState: StateFlow<GetBrokerProfileState> = _getBrokerProfileState.asStateFlow()

    private val _getBrokerAvailabilityState = MutableStateFlow<GetBrokerAvailabilityState>(GetBrokerAvailabilityState.Empty)
    val getBrokerAvailabilityState: StateFlow<GetBrokerAvailabilityState> = _getBrokerAvailabilityState.asStateFlow()




    fun get_brokers(){

        _getBrokersState.value = GetBrokersState.Loading

        viewModelScope.launch {
            try{
                val response = repo.getBrokers()
                if(response.isSuccessful && response.body()!=null){
                    _getBrokersState.value = GetBrokersState.Success(response.body())
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

    fun getBrokerProfile(id:String){
        _getBrokerProfileState.value = GetBrokerProfileState.Loading
        viewModelScope.launch {
            try {
                val response = repo.getBrokerProfile(id)

                if (response.isSuccessful && response.body() != null) {
                    _getBrokerProfileState.value = GetBrokerProfileState.Success(response.body()!!)
                }
                else {
                    _getBrokerProfileState.value = GetBrokerProfileState.Error(response.message())
                }

                } catch (e: Exception) {
                _getBrokerProfileState.value = GetBrokerProfileState.Error(e.message.toString())
            }


        }
    }

    fun getBrokerAvailability(id:String){
        _getBrokerAvailabilityState.value = GetBrokerAvailabilityState.Loading
        viewModelScope.launch {
            try {
                val response = repo.getBrokerAvailability(id)
                if (response.isSuccessful && response.body() != null) {
                    _getBrokerAvailabilityState.value = GetBrokerAvailabilityState.Success(response.body()!!)
                } else {
                    _getBrokerAvailabilityState.value = GetBrokerAvailabilityState.Error(response.message())
                }
            } catch (e: Exception) {
                _getBrokerAvailabilityState.value = GetBrokerAvailabilityState.Error(e.message.toString())
            }
                }
        }
    }



