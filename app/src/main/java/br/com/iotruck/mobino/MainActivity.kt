package br.com.iotruck.mobino

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val handle = Handler()
        handle.postDelayed(Runnable() {
            run() {
                startLogin()
            }
        },2000)
    }

    fun startLogin() {
        startActivity(
            Intent(this, LoginActivity::class.java),
            ActivityOptions.makeSceneTransitionAnimation(this).toBundle()
        )
    }
}