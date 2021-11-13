package br.com.iotruck.mobino.feature.home.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import br.com.iotruck.mobino.R
import br.com.iotruck.mobino.commons.db.DatabaseHandler
import br.com.iotruck.mobino.feature.account.view.AccountActivity
import br.com.iotruck.mobino.feature.login.model.Trucker
import br.com.iotruck.mobino.feature.login.services.TruckerService
import br.com.iotruck.mobino.feature.login.view.LoginActivity
import br.com.iotruck.mobino.feature.schedule.view.ScheduleActivity

class HomeActivity() : AppCompatActivity() {

    var trucker: Trucker = DatabaseHandler.getAllTrucker().get(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val tvNameTrucker: TextView = findViewById(R.id.tv_trucker_name)
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
}