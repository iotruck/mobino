package br.com.iotruck.mobino.feature.login.model

import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class Trucker(
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("cpf")
    var cpf: String,
    @SerializedName("cnh")
    var cnh: String,
    @SerializedName("birthDate")
    var birthDate: LocalDate,
    @SerializedName("phoneNumber")
    var phoneNumber: String
)
