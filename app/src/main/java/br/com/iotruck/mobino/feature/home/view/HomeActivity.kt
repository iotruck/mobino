package br.com.iotruck.mobino.feature.home.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isGone
import br.com.iotruck.mobino.R
import br.com.iotruck.mobino.commons.db.DatabaseHandler
import br.com.iotruck.mobino.feature.account.view.AccountActivity
import br.com.iotruck.mobino.feature.login.model.Trucker
import br.com.iotruck.mobino.feature.login.view.LoginActivity
import br.com.iotruck.mobino.feature.maps.view.MapsActivity
import br.com.iotruck.mobino.feature.schedule.view.ScheduleActivity
import br.com.iotruck.mobino.model.Travel

class HomeActivity() : AppCompatActivity() {

    var trucker: Trucker = DatabaseHandler.getAllTrucker().get(0)
    lateinit var travel: Travel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val tvNameTrucker: TextView = findViewById(R.id.tv_user_name)
        tvNameTrucker.text = trucker.name
        var tvCodeTravel: TextView = findViewById(R.id.tv_code_travel)
        var tvRetomar: TextView = findViewById(R.id.tv_retomar)
        var ivBtn: ImageView = findViewById(R.id.iv_play)
        if (intent.getSerializableExtra("lastTravel") != null) {
            travel = intent.getSerializableExtra("lastTravel") as Travel
            tvCodeTravel.text = travel.code
        } else {
            tvCodeTravel.text = getString(R.string.testado)
            tvRetomar.text = ""
            ivBtn.isGone = true
        }
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

    fun goToTravel(v: View) {
        var entity = Intent(this, MapsActivity::class.java)

        entity.putExtra("travel", travel)

        startActivity(entity)
    }
}

