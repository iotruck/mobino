package br.com.iotruck.mobino.feature.schedule.services.interfaces


import br.com.iotruck.mobino.model.Travel
import retrofit2.Call
import retrofit2.http.*

interface TravelServiceInterface {

    @Headers("Content-Type: application/json")
    @GET("travel/trucker/{id}")
    fun getTravels(@Path("id") id : Int): Call<MutableList<Travel>>
}