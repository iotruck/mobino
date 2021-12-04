package br.com.iotruck.mobino.feature.maps.services.interfaces

import br.com.iotruck.mobino.model.Travel
import br.com.iotruck.mobino.model.Location
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.PUT
import retrofit2.http.Path

interface MapsServiceInterface {

    @Headers("Content-Type: application/json")
    @PUT("/location/{id}")
    fun putLocation(@Path("id") id: Int, @Body location: Location): Call<Unit>

    @Headers("Content-Type: application/json")
    @PUT("/travel/{id}")
    fun putTravel(@Path("id") id: Int, @Body travel: Travel): Call<Travel>
}