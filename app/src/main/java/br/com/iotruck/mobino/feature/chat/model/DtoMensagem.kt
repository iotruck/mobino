package br.com.iotruck.mobino.feature.chat.model

import br.com.iotruck.mobino.model.Travel
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.time.LocalDate

data class DtoMensagem (
    @SerializedName("content")
    var content: String,
    @SerializedName("sender")
    var sender: String,
    @SerializedName("travel")
    var travel: Travel
) : Serializable