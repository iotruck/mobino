package br.com.iotruck.mobino.feature.schedule.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import br.com.iotruck.mobino.R
import br.com.iotruck.mobino.commons.db.DatabaseHandler
import br.com.iotruck.mobino.feature.home.view.HomeActivity
import br.com.iotruck.mobino.feature.login.model.Trucker
import br.com.iotruck.mobino.model.Travel
import br.com.iotruck.mobino.feature.schedule.services.TravelService

class ScheduleActivity : AppCompatActivity() {

    val apiService = TravelService()
    val trucker : Trucker = DatabaseHandler.getAllTrucker().get(0)
    private lateinit var newRecyclerViewFuture: RecyclerView
    var travels : MutableList<Travel> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)
        newRecyclerViewFuture = findViewById(R.id.recyclerViewFutures)

        apiService.getTravels(travels, trucker, newRecyclerViewFuture,this,this)
        newRecyclerViewFuture.setHasFixedSize(false)

    }


    fun goToHome(v: View){
        startActivity(Intent(this, HomeActivity::class.java))
    }

}