package br.com.iotruck.mobino.feature.account.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import br.com.iotruck.mobino.R
import br.com.iotruck.mobino.commons.db.DatabaseHandler
import br.com.iotruck.mobino.feature.home.view.HomeActivity
import br.com.iotruck.mobino.feature.login.model.Trucker

class AccountActivity : AppCompatActivity() {

    var trucker : Trucker = DatabaseHandler.getAllTrucker().get(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

        val edEmail : EditText = findViewById(R.id.et_emailAccount)
        val edName : EditText = findViewById(R.id.et_nameAccount)
        val edBirth : EditText = findViewById(R.id.et_birthAccount)
        val edliscence : EditText = findViewById(R.id.et_liscenceAccount)
        val edCpf : EditText = findViewById(R.id.et_cpfAccount)

        edEmail.hint = trucker.email
        edName.hint = trucker.name
        edBirth.hint = trucker.birthDate
        edliscence.hint = trucker.cnh
        edCpf.hint = trucker.cpf


    }
    fun goToHome(v: View){
        startActivity(Intent(this, HomeActivity::class.java))
    }
}