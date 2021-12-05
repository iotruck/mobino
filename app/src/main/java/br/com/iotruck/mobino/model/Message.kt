package br.com.iotruck.mobino.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Message(
    @SerializedName("id")
    var id: Int,
    @SerializedName("content")
    var content: String,
    @SerializedName("dateTimeMessage")
    var dateTimeMessage: String,
    @SerializedName("sender")
    var sender: String,
    @SerializedName("travel")
    var travel: Travel
) : Serializable
