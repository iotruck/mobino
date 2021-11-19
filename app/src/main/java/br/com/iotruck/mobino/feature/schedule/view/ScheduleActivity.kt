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
    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<Travel>
    lateinit var travels : List<Travel>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)


        travels = apiService.getTravels(trucker, this)

        newRecyclerView = findViewById(R.id.recyclerView)
        newRecyclerView.layoutManager = LinearLayoutManager(this)
        newRecyclerView.setHasFixedSize(true)

        newArrayList = arrayListOf<Travel>()
        getTravelData()
    }

    private fun getTravelData() {
       newRecyclerView.adapter = Adapter(travels)
    }

    fun goToHome(v: View){
        startActivity(Intent(this, HomeActivity::class.java))
    }

}