package br.com.iotruck.mobino.feature.login.services

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import br.com.iotruck.mobino.commons.builder.ServiceBuilder
import br.com.iotruck.mobino.feature.login.model.Trucker
import br.com.iotruck.mobino.feature.login.model.TruckerLogin
import br.com.iotruck.mobino.feature.login.services.interfaces.TruckerServiceInterface


class TruckerService {

    fun login(truckerLogin: TruckerLogin, onResult: (Trucker?) -> Unit) {

        val retrofit = ServiceBuilder.buildServices(TruckerServiceInterface::class.java)

        retrofit.login(truckerLogin).enqueue(

            object : Callback<Trucker> {

                override fun onFailure(call: Call<Trucker>, t: Throwable) {
                    onResult(null)
                }

                override fun onResponse(call: Call<Trucker>, response: Response<Trucker>) {
                    val trucker = response.body()
                    onResult(trucker)
                }
            }
        )
    }

}