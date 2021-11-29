package br.com.iotruck.mobino.feature.home.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import br.com.iotruck.mobino.R
import br.com.iotruck.mobino.commons.db.DatabaseHandler
import br.com.iotruck.mobino.feature.account.view.AccountActivity
import br.com.iotruck.mobino.feature.login.model.Trucker
import br.com.iotruck.mobino.feature.login.view.LoginActivity
import br.com.iotruck.mobino.feature.maps.model.Travel
import br.com.iotruck.mobino.feature.maps.view.MapsActivity
import br.com.iotruck.mobino.feature.schedule.model.Analyst
import br.com.iotruck.mobino.feature.schedule.model.Location
import br.com.iotruck.mobino.feature.schedule.model.Truck
import br.com.iotruck.mobino.feature.schedule.model.TruckType
import br.com.iotruck.mobino.feature.schedule.view.ScheduleActivity

class HomeActivity() : AppCompatActivity() {

    var trucker: Trucker = DatabaseHandler.getAllTrucker().get(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val tvNameTrucker: TextView = findViewById(R.id.tv_user_name)
        tvNameTrucker.text = trucker.name

    }

    fun logout(v: View) {
        DatabaseHandler.deleteTrucker(trucker.id)
        startActivity(Intent(this, LoginActivity::class.java))
    }

    fun goToAccount(v: View) {
        startActivity(Intent(this, AccountActivity::class.java))
    }

    fun goToTravels(v: View) {
        startActivity(Intent(this, ScheduleActivity::class.java))
    }

    fun returnTravel(v: View) {
        val analyst: Analyst = Analyst(1,"Igor")
        val truckType: TruckType = TruckType.BUCKET
        val truck: Truck = Truck(1,"Igor","Sei la",truckType)
        val location: Location = Location(7,"Rua dos bobos",0.0,0.0)
        val trucker: Trucker = Trucker(1,"Igor","igor","32323","231213","2021-21-11","32321312")
        val travel: Travel = Travel(3,analyst,"Code Travel","READY",location,"2021-01-21","d",location,truck,trucker)

        val activityTravel: Intent = Intent(this, MapsActivity::class.java)

        activityTravel.putExtra("travel",travel)
        startActivity(activityTravel)
    }


}

