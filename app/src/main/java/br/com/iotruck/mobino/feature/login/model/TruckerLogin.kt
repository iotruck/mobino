package br.com.iotruck.mobino.feature.login.model

import com.google.gson.annotations.SerializedName

data class TruckerLogin(
    @SerializedName("email")
    var email: String,
    @SerializedName("password")
    var password: String
)
