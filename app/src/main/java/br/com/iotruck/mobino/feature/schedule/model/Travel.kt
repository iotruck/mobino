package br.com.iotruck.mobino.feature.schedule.model

import br.com.iotruck.mobino.feature.login.model.Trucker
import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class Travel(
    @SerializedName("Id")
    var id : Int,
    @SerializedName("Analyst")
    var analyst : Analyst,
    @SerializedName("Code")
    var code : String,
    @SerializedName("Current_Truck_Position")
    var currentTruckPosition : Location,
    @SerializedName("Date_Travel")
    var dateTravel : LocalDate,
    @SerializedName("Description")
    var description : String,
    @SerializedName("Destiny")
    var destiny : Location,
    @SerializedName("Estimated_Value")
    var estimatedValue : Double,
    @SerializedName("Status")
    var status : String,
    @SerializedName("Truck")
    var truck: Truck,
    @SerializedName("Trucker")
    var trucker: Trucker
)
