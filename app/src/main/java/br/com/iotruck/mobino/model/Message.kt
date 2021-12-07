package br.com.iotruck.mobino.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.time.LocalDate

data class Message(
    @SerializedName("content")
    var content: String,
    @SerializedName("dateTimeMessage")
    var dateTimeMessage: String,
    @SerializedName("sender")
    var sender: String,
    @SerializedName("travel")
    var travel: Travel
) : Serializable
