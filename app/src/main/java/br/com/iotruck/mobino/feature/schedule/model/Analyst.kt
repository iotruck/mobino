package br.com.iotruck.mobino.feature.schedule.model

import com.google.gson.annotations.SerializedName

data class Analyst(
    @SerializedName("Id")
    var id : Int,
    @SerializedName("Name")
    var name : String)

