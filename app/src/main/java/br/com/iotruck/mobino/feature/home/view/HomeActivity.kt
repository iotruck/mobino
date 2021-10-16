package br.com.iotruck.mobino.feature.home.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import br.com.iotruck.mobino.R
import br.com.iotruck.mobino.feature.login.view.LoginActivity

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    fun logout(v: View){
        startActivity(Intent(this, LoginActivity::class.java))
    }
}