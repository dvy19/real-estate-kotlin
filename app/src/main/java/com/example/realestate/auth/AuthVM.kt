package com.example.realestate.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


sealed class RegisterState{
    object Loading:RegisterState()
    object Empty:RegisterState()
    data class Success(val data:SignupRes):RegisterState()
    data class Error(val message:String):RegisterState()
}
class AuthVM():ViewModel() {

    val repo=AuthRepo()

    private val _registerState= MutableStateFlow<RegisterState>(RegisterState.Empty)
    val registerState=_registerState.asStateFlow()

    fun register(req: SignupRequest){

        _registerState.value=RegisterState.Loading

        viewModelScope.launch{

            try{

                val response=repo.registerUser(req)

                if(response.isSuccessful && response.body()!=null){
                    _registerState.value=RegisterState.Success(response.body()!!)
                }else{
                    _registerState.value=RegisterState.Error(response.message())
                }
            }
            catch(e:Exception){
                _registerState.value=RegisterState.Error(e.message.toString())
                }
            }
        }

    fun login(req: LoginReq){

        _registerState.value=RegisterState.Loading

        viewModelScope.launch{

            try{
                val response=repo.loginUser(req)

                if(response.isSuccessful && response.body()!=null){
                    _registerState.value=RegisterState.Success(response.body()!!)
                }else{
                    _registerState.value=RegisterState.Error(response.message())
                    }
            }
            catch(e:Exception){
                _registerState.value=RegisterState.Error(e.message.toString())
                }

        }
    }


}





