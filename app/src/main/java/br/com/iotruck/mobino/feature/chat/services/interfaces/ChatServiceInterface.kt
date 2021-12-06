package br.com.iotruck.mobino.feature.chat.services.interfaces

import br.com.iotruck.mobino.model.Message
import retrofit2.Call
import retrofit2.http.*

interface ChatServiceInterface {

    @Headers("Content-Type: application/json")
    @GET("feed/message/{id}")
    fun getMessages(@Path("id") id: Int, @Query("qtd") qtd: Int): Call<MutableList<Message>>

    @Headers("Content-Type: application/json")
    @POST("feed/message")
    fun postMessages(@Body message: Message): Call<Message>
}