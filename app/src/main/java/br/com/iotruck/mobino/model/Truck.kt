package br.com.iotruck.mobino.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Truck(
    @SerializedName("id")
    var id : Int,
    @SerializedName("name")
    var name : String,
    @SerializedName("truckBrand")
    var truckBrand : String,
    @SerializedName("truckType")
    var truckType: TruckType
): Serializable
