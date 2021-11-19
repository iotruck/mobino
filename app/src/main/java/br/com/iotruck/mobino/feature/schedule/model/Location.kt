package br.com.iotruck.mobino.feature.schedule.model

import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("Id")
    var id : Int,
    @SerializedName("Address")
    var address : String,
    @SerializedName("Latitude")
    var latitude : Double,
    @SerializedName("Longitude")
    var longitude : Double
)
