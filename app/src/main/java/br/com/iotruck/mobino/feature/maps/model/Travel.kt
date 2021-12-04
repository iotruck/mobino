package br.com.iotruck.mobino.feature.maps.model

import br.com.iotruck.mobino.feature.login.model.Trucker
import br.com.iotruck.mobino.feature.schedule.model.Analyst
import br.com.iotruck.mobino.feature.schedule.model.Location
import br.com.iotruck.mobino.feature.schedule.model.Truck
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Travel(
    @SerializedName("id")
    var id : Int,
    @SerializedName("analyst")
    var analyst : Analyst,
    @SerializedName("codigo")
    var code : String,
    @SerializedName("status")
    var status: String,
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
    var trucker: Trucker
) : Serializable