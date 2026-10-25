package com.example.realestate.user


data class BrokerResponse(
    var _id:String,
    var name:String,
    var office:String,
    var gender:String,
    var image:String,
    var phone:String,
    var city:String,
    var rating:Int,
    var propertyTypes:List<String>,
    var isVerified:String
)