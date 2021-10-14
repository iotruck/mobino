package br.com.iotruck.mobino.feature.login.services.interfaces

import br.com.iotruck.mobino.feature.login.model.Trucker
import br.com.iotruck.mobino.feature.login.model.TruckerLogin
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface TruckerServiceInterface {

    @Headers("Content-Type: application/json")
    @POST("trucker/login")
    fun login(@Body truckerLogin: TruckerLogin): Call<Trucker>
}