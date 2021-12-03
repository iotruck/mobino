package br.com.iotruck.mobino.feature.schedule.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.iotruck.mobino.R
import br.com.iotruck.mobino.commons.db.DatabaseHandler
import br.com.iotruck.mobino.feature.home.view.HomeActivity
import br.com.iotruck.mobino.feature.login.model.Trucker
import br.com.iotruck.mobino.feature.schedule.adapter.Adapter
import br.com.iotruck.mobino.feature.schedule.model.Travel
import br.com.iotruck.mobino.feature.schedule.services.TravelService

class ScheduleActivity : AppCompatActivity() {

    val apiService = TravelService()
    val trucker : Trucker = DatabaseHandler.getAllTrucker().get(0)
    private lateinit var newRecyclerViewToday: RecyclerView
    private lateinit var newRecyclerViewFuture: RecyclerView
    var travels : MutableList<Travel> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)

        newRecyclerViewToday = findViewById(R.id.recyclerViewToday)
        apiService.getTravels(travels, trucker, newRecyclerViewToday, newRecyclerViewFuture, this)
        newRecyclerViewToday.setHasFixedSize(true)

    }


    fun goToHome(v: View){
        startActivity(Intent(this, HomeActivity::class.java))
    }

}