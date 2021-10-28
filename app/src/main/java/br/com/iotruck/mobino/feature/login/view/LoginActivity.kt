package br.com.iotruck.mobino.feature.login.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.iotruck.mobino.R
import br.com.iotruck.mobino.feature.login.model.TruckerLogin
import br.com.iotruck.mobino.feature.login.services.TruckerService


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

                email = etLogin.text.toString().trim(),
                password = etPassword.text.toString().trim()
            )

            try {

                apiService.login(truckerLogin, this)

            } catch (e: Exception) {

                Toast.makeText(
                    this,
                    "Erro de login, algo inesperado aconteceu, tente novamente!",
                    Toast.LENGTH_SHORT
                ).show()
                Log.e("LoginError", "Erro ao executar o login - throwable: ${e.message}")

            }
        } else {

            Toast.makeText(this, "Preencha os campos corretamete", Toast.LENGTH_SHORT).show()

        }
    }
}