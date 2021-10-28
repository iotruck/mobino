package br.com.iotruck.mobino.feature.login.services

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import br.com.iotruck.mobino.commons.builder.ServiceBuilder
import br.com.iotruck.mobino.feature.home.view.HomeActivity
import br.com.iotruck.mobino.feature.login.model.Trucker
import br.com.iotruck.mobino.feature.login.model.TruckerLogin
import br.com.iotruck.mobino.feature.login.services.interfaces.TruckerServiceInterface


class TruckerService {

    private val retrofit = ServiceBuilder.buildServices(TruckerServiceInterface::class.java)

    fun login(truckerLogin: TruckerLogin, pakageContext: Context) {

        retrofit.login(truckerLogin).enqueue(

            object : Callback<Trucker> {

                override fun onResponse(call: Call<Trucker>, response: Response<Trucker>) {

                    if (response.isSuccessful) {
                        val redirect = Intent(pakageContext, HomeActivity::class.java)
                        redirect.putExtra("truckerInfo", response.body())
                        startActivity(pakageContext, redirect, null)
                    } else {
                        Log.e(
                            "LoginError",
                            "Erro ao executar o login - status: ${response.code()} errorBody: ${response.errorBody()} message: ${response.message()}"
                        )
                    }
                }
                override fun onFailure(call: Call<Trucker>, t: Throwable) {
                    throw Exception("message ${t.message}")
                }
            }
        )
    }
}