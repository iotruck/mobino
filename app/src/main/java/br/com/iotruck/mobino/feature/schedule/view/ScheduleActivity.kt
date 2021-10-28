package br.com.iotruck.mobino.feature.schedule.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import br.com.iotruck.mobino.R
import br.com.iotruck.mobino.feature.home.view.HomeActivity

class ScheduleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)
    }
    fun goToHome(v: View){
        startActivity(Intent(this, HomeActivity::class.java))
    }

}