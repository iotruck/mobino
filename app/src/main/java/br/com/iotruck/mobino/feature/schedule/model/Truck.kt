package br.com.iotruck.mobino.feature.schedule.model

import com.google.gson.annotations.SerializedName

data class Truck(
    @SerializedName("Id")
    var id : Int,
    @SerializedName("Name")
    var name : String,
    @SerializedName("Truck_Brand")
    var truckBrand : String,
    @SerializedName("Truck_Type")
    var truckType: TruckType
)
