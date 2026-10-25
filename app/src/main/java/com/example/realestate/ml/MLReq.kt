package com.example.realestate.ml

/*
{
  "area": 1500,
  "flooers": 2,
  "bedrooms": 3,
  "bathrooms": 2,
  "bhk": 3,
  "property_type": "Apartment",
  "furnished": "Semi-Furnished",
  "parking": "Yes",
  "city": "Delhi"
}
 */

data class PricePredictReq(
    var area: Double,
    var flooers: Int,
    var bedrooms: Int,
    var bathrooms:Int,
    var bhk:Int,
    var property_type:String,
    var furnished:String,
    var parking: String,
    var city:String
)
data class PriceRange(
    val min: Double,
    val max: Double
)

data class PredictPriceResponse(
    val predictedPrice: Double,
    val priceRange: PriceRange,
    val count: Int,
    //val properties: List<House>
)


data class RagMsgReq(
    var question:String
)

data class RagChatResponse(
    val message: String,
    val data: RagChat
)

data class RagChat(
    val _id: String,
    val user: String,
    val question: String,
    val answer: String
)

