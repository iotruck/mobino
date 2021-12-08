package br.com.iotruck.mobino.feature.chat.view

import android.content.Intent
import android.os.*
import androidx.appcompat.app.AppCompatActivity


import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import br.com.iotruck.mobino.R
import br.com.iotruck.mobino.commons.db.DatabaseHandler
import br.com.iotruck.mobino.feature.chat.model.DtoMensagem
import br.com.iotruck.mobino.feature.chat.services.ChatService
import br.com.iotruck.mobino.feature.maps.view.MapsActivity
import br.com.iotruck.mobino.feature.schedule.view.ScheduleActivity
import br.com.iotruck.mobino.model.Message
import br.com.iotruck.mobino.model.Travel
import java.time.LocalDate
import java.time.ZoneId

class ActivityChat : AppCompatActivity() {
    val apiService = ChatService()

    var postStopped = false

    val trucker = DatabaseHandler.getAllTrucker().get(0)

    var messages: MutableList<Message> = mutableListOf()

    private lateinit var newRecyclerView: RecyclerView

    lateinit var travel: Travel

    val handle = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        travel = intent.getSerializableExtra("travel") as Travel

        val tvCodeTravel: TextView = findViewById(R.id.tv_description_feed)
        tvCodeTravel.text = travel.code

        newRecyclerView = findViewById(R.id.container_message)

        postDelay()
    }

    fun postDelay() {
        handle.postDelayed(Runnable() {
            run() {
                apiService.getMessage(messages, newRecyclerView, travel.id, this)
                if (!postStopped) {
                    postDelay()

                }
            }
        }, 2000)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun sendMessage(v: View) {
        val etMessage: EditText = findViewById(R.id.input_box)
        val message = DtoMensagem(etMessage.text.toString(), trucker.name, travel)
        apiService.postMessage(messages, message, newRecyclerView, this)
        etMessage.text.clear()
    }

    fun goToMap(v: View) {
        postStopped = true

        var entity = Intent(this, MapsActivity::class.java)

        entity.putExtra("travel", travel)

        startActivity(entity)

    }

    override fun onBackPressed() {
        postStopped = true

        var entity = Intent(this, MapsActivity::class.java)

        entity.putExtra("travel", travel)

        startActivity(entity)

        super.onBackPressed()
    }

}