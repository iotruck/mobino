package br.com.iotruck.mobino.feature.home.services

import android.content.Context
import br.com.iotruck.mobino.commons.builder.ServiceBuilder
import br.com.iotruck.mobino.commons.network.NetworkStatus
import br.com.iotruck.mobino.feature.home.services.interfaces.HomeServiceInterface
import br.com.iotruck.mobino.feature.login.model.Trucker
import br.com.iotruck.mobino.model.Travel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeService {

    private val retrofit = ServiceBuilder.buildServices(HomeServiceInterface::class.java)

    fun getLastTravel(trucker: Trucker, packgeContext: Context){

        if (NetworkStatus.isConnected(packgeContext)){
            retrofit.getLastTravel(trucker.id).enqueue(

                object : Callback<Travel>{
                    override fun onResponse(call: Call<Travel>, response: Response<Travel>) {

                    }

                    override fun onFailure(call: Call<Travel>, t: Throwable) {
                        TODO("Not yet implemented")
                    }
                }
            )
        }
    }
}