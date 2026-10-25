package com.example.realestate.auth

data class SignupRequest(

    var name:String,
    var email:String,
    var role:String="user",
    var password:String
)

data class SignupRes(

    var message:String,
    var token:String,
    var user:UserData
)

data class UserData(
    var id:String,
    var name:String,
    var email:String,
    var role:String
)


data class LoginReq(
    var email: String,
    var password:String
)