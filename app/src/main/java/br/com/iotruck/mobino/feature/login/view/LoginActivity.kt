package br.com.iotruck.mobino.feature.login.view

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.iotruck.mobino.R
import br.com.iotruck.mobino.commons.network.NetWorkStatus
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
                if(NetWorkStatus.isConnected(this)){
                    apiService.login(truckerLogin,this)
                }else{
                    Toast.makeText(this, "Não há conexão com a internet", Toast.LENGTH_SHORT).show()
                }

            } catch (e: Exception) {

                Toast.makeText(this, "Erro de login, algo inesperado aconteceu, tente novamente!", Toast.LENGTH_SHORT).show()
                Log.e("LoginError", "Erro ao executar o login - throwable: ${e.message}")

            }
        } else {

            Toast.makeText(this, "Preencha os campos corretamete", Toast.LENGTH_SHORT).show()

        }
    }
}