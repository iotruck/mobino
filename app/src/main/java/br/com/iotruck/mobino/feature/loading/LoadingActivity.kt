package br.com.iotruck.mobino.feature.loading

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.iotruck.mobino.R
import br.com.iotruck.mobino.commons.db.DatabaseHandler
import br.com.iotruck.mobino.feature.home.services.HomeService
import br.com.iotruck.mobino.feature.login.model.Trucker

class LoadingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)
        semideia()
    }

    fun semideia(){
        val homeService = HomeService()
        val trucker : Trucker = DatabaseHandler.getAllTrucker().get(0)
        homeService.getLastTravel(trucker, this)

    }
}