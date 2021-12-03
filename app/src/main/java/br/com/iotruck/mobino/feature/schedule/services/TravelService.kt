package br.com.iotruck.mobino.feature.schedule.services

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.iotruck.mobino.commons.builder.ServiceBuilder
import br.com.iotruck.mobino.commons.network.NetworkStatus
import br.com.iotruck.mobino.feature.login.model.Trucker
import br.com.iotruck.mobino.feature.schedule.adapter.Adapter
import br.com.iotruck.mobino.feature.schedule.adapter.AdapterFutures
import br.com.iotruck.mobino.feature.schedule.model.Travel
import br.com.iotruck.mobino.feature.schedule.services.interfaces.TravelServiceInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class TravelService {

    private val retrofit = ServiceBuilder.buildServices(TravelServiceInterface::class.java)

    fun getTravels(travels:MutableList<Travel>,trucker : Trucker ,newRecyclerViewToday : RecyclerView,
                   newRecyclerViewFuture : RecyclerView,packgeContext : Context) {

        if (NetworkStatus.isConnected(packgeContext)){
            retrofit.getTravels(trucker.id).enqueue(

                object : Callback<MutableList<Travel>> {

                    override fun onResponse(call: Call<MutableList<Travel>>,response: Response<MutableList<Travel>>){

                        if (response.isSuccessful){
                            travels.addAll(response.body() as MutableList<Travel>)
                            newRecyclerViewToday.adapter = Adapter(travels)
                            newRecyclerViewToday.layoutManager = LinearLayoutManager(packgeContext)
                            newRecyclerViewFuture.adapter = AdapterFutures(travels)
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