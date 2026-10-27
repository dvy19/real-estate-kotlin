package com.example.realestate.user

data class AllBrokerResponse(
    var message:String,
    var brokers:List<BrokerData>
)
data class BrokerProfileResponse(
    var message: String,
    var broker: BrokerData
)
data class BrokerData(
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

data class BrokerAvailable(
    var message:String,
    var availability:Availability
)

data class Availability(
    var broker:String,
    var createdAt:String,

    var updatedAt:String,

    var schedule:List<ScheduleData>,

    var _id: String
)

data class ScheduleData(
    var day:String,
    var startTime:String,
    var endTime:String,
    var date:String,
    var _id:String,
    var isAvailable:Boolean
)

data class AppointmentRequest(
    var broker:String,
    var startTime:String,
    var endTime:String,
    var appointmentDate:String,
    var status:String

)

data class AppointmentResponse(
    var message:String,
    var appointment:Appointment
)

data class Appointment(
    var broker:String,
    var createdAt:String,
    var updatedAt:String,
    var startTime:String,
    var endTime:String,
    var appointmentDate:String,
    var status:String,
    var _id:String,
    var user:String,
)