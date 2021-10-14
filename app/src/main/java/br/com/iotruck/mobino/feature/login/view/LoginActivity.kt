package br.com.iotruck.mobino.feature.login.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.iotruck.mobino.R
import br.com.iotruck.mobino.feature.login.model.TruckerLogin
import br.com.iotruck.mobino.feature.login.services.TruckerService
import br.com.iotruck.mobino.feature.main.view.MainActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun login(v: View) {
        val apiService = TruckerService()
        val etLogin: EditText = findViewById(R.id.et_email)
        val etPassword: EditText = findViewById(R.id.et_password)

        if (!etLogin.text.toString().isEmpty() && !etPassword.text.toString().isEmpty()) {

            val truckerLogin = TruckerLogin(
                email = etLogin.text.toString(),
                password = etPassword.text.toString()
            )

            apiService.login(truckerLogin) {
                if (it?.id != null) {
                    startActivity(Intent(this, MainActivity::class.java))
                } else {
                    Toast.makeText(this, "O usuario é invalido", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            Toast.makeText(this, "Preencha os campos corretamete", Toast.LENGTH_SHORT).show()
        }
    }
}