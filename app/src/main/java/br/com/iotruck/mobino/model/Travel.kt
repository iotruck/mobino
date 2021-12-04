package br.com.iotruck.mobino.model

import br.com.iotruck.mobino.model.Trucker
import br.com.iotruck.mobino.model.Analyst
import br.com.iotruck.mobino.model.Location
import br.com.iotruck.mobino.model.enum.Status
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Travel(
    @SerializedName("id")
    var id : Int,
    @SerializedName("analyst")
    var analyst : Analyst,
    @SerializedName("codigo")
    var code : String,
    @SerializedName("currentTruckPosition")
    var currentTruckPosition : Location,
    @SerializedName("dateTravel")
    var dateTravel : String,
    @SerializedName("description")
    var description : String,
    @SerializedName("destiny")
    var destiny : Location,
    @SerializedName("truck")
    var truck: Truck,
    @SerializedName("trucker")
    var trucker: Trucker,
    @SerializedName("status")
    var status: Status
): Serializable
