package br.com.iotruck.mobino.feature.chat.services

import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.iotruck.mobino.commons.builder.ServiceBuilder
import br.com.iotruck.mobino.commons.db.DatabaseHandler
import br.com.iotruck.mobino.commons.network.NetworkStatus
import br.com.iotruck.mobino.feature.chat.adapter.AdapterMessage
import br.com.iotruck.mobino.feature.chat.model.DtoMensagem
import br.com.iotruck.mobino.feature.chat.services.interfaces.ChatServiceInterface
import br.com.iotruck.mobino.feature.loading.LoadingActivity
import br.com.iotruck.mobino.feature.login.model.Trucker
import br.com.iotruck.mobino.feature.login.model.TruckerLogin
import br.com.iotruck.mobino.feature.login.services.interfaces.TruckerServiceInterface
import br.com.iotruck.mobino.feature.schedule.adapter.AdapterFutures
import br.com.iotruck.mobino.model.Message
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.ZoneId

class ChatService {

    private val retrofit = ServiceBuilder.buildServices(ChatServiceInterface::class.java)

    fun getMessage(
        messages: MutableList<Message>,
        newMessagesRecyclerView: RecyclerView,
        id: Int,
        packageContext: Context
    ) {
        if (NetworkStatus.isConnected(packageContext)) {

            retrofit.getMessages(id, messages.size).enqueue(

                object : Callback<MutableList<Message>> {

                    override fun onResponse(
                        call: Call<MutableList<Message>>, response: Response<MutableList<Message>>
                    ) {
                        if (response.isSuccessful) {
                            if(!response.body().isNullOrEmpty()) {
                                messages.addAll(response.body() as MutableList<Message>)
                                newMessagesRecyclerView.adapter = AdapterMessage(messages)
                                newMessagesRecyclerView.layoutManager = LinearLayoutManager(packageContext)
                            }
                        } else {
                            Log.e(
                                "MessageError",
                                "Erro ao buscar mensagens - status: ${response.code()} " +
                                        "errorBody: ${response.errorBody()} message: ${response.message()}"
                            )
                            Toast.makeText(
                                packageContext,
                                "Erro ao Carregar mensagens",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<MutableList<Message>>, t: Throwable) {
                        throw Exception("message ${t.message}")
                    }

                }
            )

        } else {

            Toast.makeText(packageContext, "Não há conexão com a internet", Toast.LENGTH_SHORT)
                .show()

        }

    }

    fun postMessage(messages : MutableList<Message>,
                    dtoMensagem: DtoMensagem,
                    newMessagesRecyclerView: RecyclerView,
                    packageContext: Context){

        if (NetworkStatus.isConnected(packageContext)) {

            retrofit.postMessages(dtoMensagem).enqueue(

                object : Callback<Void> {

                    @RequiresApi(Build.VERSION_CODES.O)
                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        if (response.isSuccessful) {
                            val z: ZoneId = ZoneId.of("America/Sao_Paulo")
                            val today: LocalDate = LocalDate.now(z)
                            val message = Message(dtoMensagem.content,today.toString(),dtoMensagem.sender, dtoMensagem.travel)
                            messages.add(message)
                            newMessagesRecyclerView.adapter = AdapterMessage(messages)
                            newMessagesRecyclerView.layoutManager = LinearLayoutManager(packageContext)
                        } else {
                            Log.e(
                                "MessageError",
                                "Erro ao enviar mensagem - status: ${response.code()} " +
                                        "errorBody: ${response.errorBody()} message: ${response.message()}"
                            )
                            Toast.makeText(
                                packageContext,
                                "Erro ao enviar mensagens",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        throw Exception("message ${t.message}")
                    }
                }

            )
        }
    }
}