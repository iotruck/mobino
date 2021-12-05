package br.com.iotruck.mobino.feature.account.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import br.com.iotruck.mobino.R
import br.com.iotruck.mobino.commons.db.DatabaseHandler
import br.com.iotruck.mobino.feature.home.view.HomeActivity
import br.com.iotruck.mobino.feature.login.model.Trucker
import br.com.iotruck.mobino.model.Travel

class AccountActivity : AppCompatActivity() {

    var trucker : Trucker = DatabaseHandler.getAllTrucker().get(0)
    lateinit var travel : Travel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

        val edEmail : EditText = findViewById(R.id.et_emailAccount)
        val tvName : TextView = findViewById(R.id.tv_nameAccount)
        val tvBirth : TextView = findViewById(R.id.tv_birthAccount)
        val tvPhoneNumber : TextView = findViewById(R.id.tv_phoneNumber)
        val tvliscence : TextView = findViewById(R.id.tv_liscenceAccount)
        val tvCpf : TextView = findViewById(R.id.tv_cpfAccount)
        if (intent.getSerializableExtra("travelToday") != null){
            travel = intent.getSerializableExtra("travelToday") as Travel
        }

        edEmail.hint = trucker.email
        tvPhoneNumber.text = trucker.phoneNumber
        tvName.text = trucker.name
        tvBirth.text = trucker.birthDate
        tvliscence.text = trucker.cnh
        tvCpf.text = trucker.cpf


    }
    fun goToHome(v: View){
        var entity = Intent(this, HomeActivity::class.java)

        if(::travel.isInitialized){
            entity.putExtra("lastTravel", travel)
        }

        startActivity(entity)
    }
}