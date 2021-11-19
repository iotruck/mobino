package br.com.iotruck.mobino.feature.schedule.services

import android.app.usage.NetworkStats
import android.content.Context
import android.net.Network
import android.util.Log
import android.widget.Toast
import br.com.iotruck.mobino.commons.builder.ServiceBuilder
import br.com.iotruck.mobino.commons.network.NetworkStatus
import br.com.iotruck.mobino.feature.login.model.Trucker
import br.com.iotruck.mobino.feature.schedule.model.Travel
import br.com.iotruck.mobino.feature.schedule.services.interfaces.TravelServiceInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception


class TravelService {

    private val retrofit = ServiceBuilder.buildServices(TravelServiceInterface::class.java)
    lateinit var travelList : MutableList<Travel>

    fun getTravels(trucker : Trucker ,packgeContext : Context) : List<Travel>{

        if (NetworkStatus.isConnected(packgeContext)){
            retrofit.getTravels(trucker.id).enqueue(

                object : Callback<List<Travel>> {

                    override fun onResponse(call: Call<List<Travel>>,response: Response<List<Travel>>){

                        if (response.isSuccessful){
                            travelList = response.body() as MutableList<Travel>
                        }else {
                            Log.e(
                                "ListError",
                                "Erro ao executar get Travel - status: ${response.code()} errorBody:" +
                                        " ${response.errorBody()} message: ${response.message()}"
                            )

                            travelList = mutableListOf()

                        }
                    }

                    override fun onFailure(call: Call<List<Travel>>, t: Throwable) {
                        throw Exception("message ${t.message}")
                        travelList = mutableListOf()
                    }
                }

            )
        }

       return travelList
    }
}