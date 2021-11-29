package br.com.iotruck.mobino.feature.maps.services

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import br.com.iotruck.mobino.commons.builder.ServiceBuilder
import br.com.iotruck.mobino.commons.network.NetworkStatus
import br.com.iotruck.mobino.feature.home.view.HomeActivity
import br.com.iotruck.mobino.feature.maps.model.Travel
import br.com.iotruck.mobino.feature.maps.services.interfaces.MapsServiceInterface
import br.com.iotruck.mobino.feature.schedule.model.Location

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MapsService {

    private val retrofit = ServiceBuilder.buildServices(MapsServiceInterface::class.java)

    fun updateLocation(id: Int, location: Location, pakageContext: Context) {

        if(NetworkStatus.isConnected(pakageContext)){

            retrofit.putLocation(id, location).enqueue(

                object : Callback<Unit> {

                    override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                        if (response.isSuccessful) {
                            println("Put location success")
                        } else {
                            println("Put location error")
                        }
                    }

                    override fun onFailure(call: Call<Unit>, t: Throwable) {
                        println(t.message)
                    }
                }
            )

        }else{
            println("Network error")
        }
    }

    fun finishTravel(id: Int, travel: Travel, pakageContext: Context) {
        if(NetworkStatus.isConnected(pakageContext)){

            retrofit.putTravel(id, travel).enqueue(

                object : Callback<Travel> {

                    override fun onResponse(call: Call<Travel>, response: Response<Travel>) {
                        if (response.isSuccessful) {
                            startActivity(pakageContext, Intent(pakageContext,HomeActivity::class.java), null)
                        } else {
                            Toast.makeText(pakageContext, "Não foi possível finalizar, tente novamente", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<Travel>, t: Throwable) {
                        println(t.message)
                    }

                }
            )

        }else{
            Toast.makeText(pakageContext, "Não há conexão com a internet", Toast.LENGTH_SHORT).show()
        }
    }
}

