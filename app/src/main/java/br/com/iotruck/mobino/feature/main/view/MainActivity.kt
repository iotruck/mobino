package br.com.iotruck.mobino.feature.main.view

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import br.com.iotruck.mobino.R
import br.com.iotruck.mobino.commons.db.DatabaseHandler
import br.com.iotruck.mobino.feature.home.view.HomeActivity
import br.com.iotruck.mobino.feature.login.model.Trucker
import br.com.iotruck.mobino.feature.login.view.LoginActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DatabaseHandler.initDatabaseInstance(this)
        val handle = Handler()
        handle.postDelayed(Runnable() {
            run() {
                session()
            }
        }, 2000)
    }

    fun session() {
        DatabaseHandler.openDatabase()
        var trucker  = DatabaseHandler.getAllTrucker()
        if (trucker.isEmpty()) {
            startLogin()
        } else {
            startActivity(
                Intent(this, HomeActivity::class.java),
                ActivityOptions.makeSceneTransitionAnimation(this).toBundle()
            )
        }
    }

    fun startLogin() {
        startActivity(
            Intent(this, LoginActivity::class.java),
            ActivityOptions.makeSceneTransitionAnimation(this).toBundle()
        )
    }
}