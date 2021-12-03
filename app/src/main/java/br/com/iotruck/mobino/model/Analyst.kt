package br.com.iotruck.mobino.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Analyst(
    @SerializedName("id")
    var id : Int,
    @SerializedName("name")
    var name : String) : Serializable

