package br.com.iotruck.mobino.feature.schedule.model

import br.com.iotruck.mobino.feature.login.model.Trucker
import com.google.gson.annotations.SerializedName
import java.time.LocalDate

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
    @SerializedName("Status")
    var status : String,
    @SerializedName("Truck")
    var truck: Truck,
    @SerializedName("Trucker")
    var trucker: Trucker
)
