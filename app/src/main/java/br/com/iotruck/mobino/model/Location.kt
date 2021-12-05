package br.com.iotruck.mobino.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Location(
    @SerializedName("id")
    var id : Int,
    @SerializedName("address")
    var address : String,
    @SerializedName("latitude")
    var latitude : Double,
    @SerializedName("longitude")
    var longitude : Double
): Serializable
