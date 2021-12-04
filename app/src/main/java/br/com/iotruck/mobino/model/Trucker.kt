package br.com.iotruck.mobino.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Trucker(
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("email")
    var email: String,
    @SerializedName("cpf")
    var cpf: String,
    @SerializedName("cnh")
    var cnh: String,
    @SerializedName("birthDate")
    var birthDate: String,
    @SerializedName("phoneNumber")
    var phoneNumber: String
) : Serializable
