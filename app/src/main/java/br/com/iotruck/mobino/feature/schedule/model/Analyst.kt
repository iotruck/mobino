package br.com.iotruck.mobino.feature.schedule.model

import com.google.gson.annotations.SerializedName

data class Analyst(
    @SerializedName("id")
    var id : Int,
    @SerializedName("name")
    var name : String)
