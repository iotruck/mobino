package br.com.iotruck.mobino.feature.login.services

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import android.window.SplashScreen
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import br.com.iotruck.mobino.commons.builder.ServiceBuilder
import br.com.iotruck.mobino.commons.db.DatabaseHandler
import br.com.iotruck.mobino.commons.network.NetworkStatus
import br.com.iotruck.mobino.feature.home.view.HomeActivity
import br.com.iotruck.mobino.feature.loading.LoadingActivity
import br.com.iotruck.mobino.feature.login.model.Trucker
import br.com.iotruck.mobino.feature.login.model.TruckerLogin
import br.com.iotruck.mobino.feature.login.services.interfaces.TruckerServiceInterface


class TruckerService{

    private val retrofit = ServiceBuilder.buildServices(TruckerServiceInterface::class.java)

    fun login(truckerLogin: TruckerLogin, pakageContext: Context) {


        if(NetworkStatus.isConnected(pakageContext)){

            retrofit.login(truckerLogin).enqueue(

                object : Callback<Trucker> {

                    override fun onResponse(call: Call<Trucker>, response: Response<Trucker>) {

                        if (response.isSuccessful) {
                            DatabaseHandler.addTrucker(response.body() as Trucker)
                            val redirect = Intent(pakageContext, LoadingActivity::class.java)
                            redirect.putExtra("truckerInfo", response.body())
                            startActivity(pakageContext, redirect, null)
                        } else {
                            Log.e(
                                "LoginError",
                                "Erro ao executar o login - status: ${response.code()} errorBody: ${response.errorBody()} message: ${response.message()}"
                            )
                            Toast.makeText(pakageContext, "Usuario ou senhas invalidos, tente novamente", Toast.LENGTH_SHORT).show()
                        }
                    }
                    override fun onFailure(call: Call<Trucker>, t: Throwable) {
                        throw Exception("message ${t.message}")
                    }
                }
            )

        }else{

            Toast.makeText(pakageContext, "Não há conexão com a internet", Toast.LENGTH_SHORT).show()

        }

    }
}