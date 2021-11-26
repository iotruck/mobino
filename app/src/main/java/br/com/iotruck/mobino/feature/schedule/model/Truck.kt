package br.com.iotruck.mobino.feature.schedule.model

import com.google.gson.annotations.SerializedName

data class Truck(
    @SerializedName("id")
    var id : Int,
    @SerializedName("name")
    var name : String,
    @SerializedName("truckBrand")
    var truckBrand : String,
    @SerializedName("truckType")
    var truckType: TruckType
)
