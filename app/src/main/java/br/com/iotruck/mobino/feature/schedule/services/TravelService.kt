package br.com.iotruck.mobino.feature.schedule.services

import android.app.Activity
import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.iotruck.mobino.R
import br.com.iotruck.mobino.commons.builder.ServiceBuilder
import br.com.iotruck.mobino.commons.network.NetworkStatus
import br.com.iotruck.mobino.feature.login.model.Trucker
import br.com.iotruck.mobino.feature.schedule.adapter.AdapterFutures
import br.com.iotruck.mobino.feature.schedule.model.Travel
import br.com.iotruck.mobino.feature.schedule.services.interfaces.TravelServiceInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*


class TravelService {

    private val retrofit = ServiceBuilder.buildServices(TravelServiceInterface::class.java)

    fun getTravels(travels:MutableList<Travel>,trucker : Trucker,
                   newRecyclerViewFuture : RecyclerView,packgeContext : Context, activity : Activity) {

        if (NetworkStatus.isConnected(packgeContext)){
            retrofit.getTravels(trucker.id).enqueue(

                object : Callback<MutableList<Travel>> {

                    @RequiresApi(Build.VERSION_CODES.O)
                    override fun onResponse(call: Call<MutableList<Travel>>, response: Response<MutableList<Travel>>){

                        if (response.isSuccessful){
                            val f: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-M-dd")
                            val z: ZoneId = ZoneId.of("America/Sao_Paulo")
                            val today: LocalDate = LocalDate.now(z)
                            val travelsFuture = mutableListOf<Travel>()
                            travels.addAll(response.body() as MutableList<Travel>)

                            for (t in travels) {

                                if(!LocalDate.parse(t.dateTravel, f).equals(today) && LocalDate.parse(t.dateTravel, f).isAfter(today) ) {
                                    travelsFuture.add(t)
                                }

                                if (LocalDate.parse(t.dateTravel, f).equals(today)) {
                                    val travelTodayName : TextView = activity.findViewById(R.id.tv_today_travel_name)
                                    val travelTodayAnalyst : TextView = activity.findViewById(R.id.tv_analyst_names_today)
                                    val travelTodayDestiny : TextView = activity.findViewById(R.id.tv_travel_destiny_today)

                                    travelTodayName.text = t.code
                                    travelTodayAnalyst.text = t.analyst.name
                                    travelTodayDestiny.text = t.destiny.address
                                }
                            }
                            newRecyclerViewFuture.adapter = AdapterFutures(travelsFuture)
                            newRecyclerViewFuture.layoutManager = LinearLayoutManager(packgeContext)

                        }else {
                            Log.e(
                                "ListError",
                                "Erro ao executar get Travel - status: ${response.code()} errorBody:" +
                                        " ${response.errorBody()} message: ${response.message()}"
                            )
                        }
                    }

                    override fun onFailure(call: Call<MutableList<Travel>>, t: Throwable) {
                        throw Exception("message ${t.message}")
                    }
                }

            )
        }

    }
}