package br.com.iotruck.mobino.feature.home.services.interfaces

import br.com.iotruck.mobino.model.Travel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface HomeServiceInterface {

    @Headers("Content-Type: application/json")
    @GET("travel/trucker/latter/{id}")
    fun getLastTravel(@Path("id") id : Int): Call<Travel>
}