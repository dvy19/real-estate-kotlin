package com.example.realestate.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


sealed class CreateAppointmentState{
    object Empty: CreateAppointmentState()
    object Loading: CreateAppointmentState()
    class Success(val data: AppointmentResponse): CreateAppointmentState()
    class Error(val message: String): CreateAppointmentState()
}
class BrokerVm ():ViewModel(){

    val repo=UserRepo()

    private val _createAppointmentState = MutableStateFlow<CreateAppointmentState>(CreateAppointmentState.Empty)
    val createAppointmentState: StateFlow<CreateAppointmentState> = _createAppointmentState.asStateFlow()

    fun createBrokerAppoint(request: AppointmentRequest){

        _createAppointmentState.value = CreateAppointmentState.Loading


        viewModelScope.launch {
            try {
                val response = repo.createBrokerAppointment(request)

                if (response.isSuccessful && response.body() != null) {
                    _createAppointmentState.value = CreateAppointmentState.Success(response.body()!!)
                } else {
                    _createAppointmentState.value = CreateAppointmentState.Error(response.message())
                }
            } catch (e: Exception) {
                _createAppointmentState.value = CreateAppointmentState.Error(e.message.toString())
            }

                }
        }
    }






