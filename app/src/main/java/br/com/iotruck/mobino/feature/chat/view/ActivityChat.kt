package br.com.iotruck.mobino.feature.chat.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper


import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import br.com.iotruck.mobino.R
import br.com.iotruck.mobino.feature.chat.services.ChatService
import br.com.iotruck.mobino.feature.schedule.view.ScheduleActivity
import br.com.iotruck.mobino.model.Message
import br.com.iotruck.mobino.model.Travel

class ActivityChat : AppCompatActivity() {
    val apiService = ChatService()

    var messages : MutableList<Message> = mutableListOf()
    var qtdMessages : Int = 0
    private lateinit var newRecyclerView: RecyclerView

    lateinit var travel: Travel
    lateinit var countDownTimer : CountDownTimer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        travel = intent.getSerializableExtra("travel") as Travel

        val tvCodeTravel : TextView = findViewById(R.id.tv_description_feed)
        tvCodeTravel.text = travel.code

        newRecyclerView = findViewById(R.id.container_message)


            countDownTimer = object : CountDownTimer(30000, 2000){
                override fun onFinish() {
                    Toast.makeText(this@ActivityChat,"Errou",Toast.LENGTH_SHORT).show()
                }

                override fun onTick(millisUntilFinished: Long) {
                    apiService.getMessage(messages,newRecyclerView,travel.id, this@ActivityChat)
                }
            }.start()


        qtdMessages = messages.size

    }

    fun goToTravels(v: View) {
        startActivity(Intent(this, ScheduleActivity::class.java))
    }

}