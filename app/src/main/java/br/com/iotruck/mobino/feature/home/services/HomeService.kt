package br.com.iotruck.mobino.feature.home.services

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import br.com.iotruck.mobino.commons.builder.ServiceBuilder
import br.com.iotruck.mobino.commons.network.NetworkStatus
import br.com.iotruck.mobino.feature.home.services.interfaces.HomeServiceInterface
import br.com.iotruck.mobino.feature.home.view.HomeActivity
import br.com.iotruck.mobino.feature.login.model.Trucker
import br.com.iotruck.mobino.model.Travel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.core.content.ContextCompat.startActivity

class HomeService {

    private val retrofit = ServiceBuilder.buildServices(HomeServiceInterface::class.java)

    fun getLastTravel(trucker: Trucker, packgeContext: Context){

        if (NetworkStatus.isConnected(packgeContext)){
            retrofit.getLastTravel(trucker.id).enqueue(

                object : Callback<Travel>{
                    override fun onResponse(call: Call<Travel>, response: Response<Travel>) {
                        if (response.isSuccessful){
                            val redirect = Intent(packgeContext, HomeActivity::class.java)
                            val travel : Travel? = response.body()
                            redirect.putExtra("lastTravel", travel)
                            startActivity(packgeContext, redirect, null)
                        }else{
                            Log.e(
                                "LoginError",
                                "Erro ao executar o login - status: ${response.code()} errorBody: ${response.errorBody()} message: ${response.message()}"
                            )
                            Toast.makeText(packgeContext, "Usuario ou senhas invalidos, tente novamente", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<Travel>, t: Throwable) {
                        throw Exception("message ${t.message}")
                    }
                }
            )
        }
    }
}