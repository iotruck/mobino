package br.com.iotruck.mobino.commons.builder

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://bino.iotruck.com.br/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun<T> buildServices(service: Class<T>): T{
        return retrofit.create(service)
    }
}