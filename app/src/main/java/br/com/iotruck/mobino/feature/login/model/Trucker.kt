package br.com.iotruck.mobino.feature.login.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

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
    var birthDate: String,
    @SerializedName("phoneNumber")
    var phoneNumber: String
) : Serializable{

    companion object {
        fun createDefaultTrucker(): Trucker{
            return Trucker(0,"","","","","")
        }
    }

}