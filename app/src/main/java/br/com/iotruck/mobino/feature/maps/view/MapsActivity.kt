package br.com.iotruck.mobino.feature.maps.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.app.Activity
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView
import androidx.core.app.ActivityCompat
import br.com.iotruck.mobino.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import br.com.iotruck.mobino.databinding.ActivityMapsBinding
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.maps.model.*
import android.view.View
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import java.text.DecimalFormat


class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var lastLocation: Location
    private lateinit var locationCallback: LocationCallback
    private lateinit var locationRequest: LocationRequest
    private lateinit var mPositionMarker: Marker

    private var locationUpdateState = false
    private val time: Long = 1000000000L
    private var timer = Timer(time)
    private var passTime: Long = 0
    private var timeTravel: Long = 0
    private var initTimeWarning: Long = 0
    private var timeWarning: Long = 0
    private var numberWarning: String = ""

    private var travelOn = true
    private var warningOn = false

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
        private const val REQUEST_CHECK_SETTINGS = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(p0: LocationResult) {
                super.onLocationResult(p0)

                lastLocation = p0.lastLocation
                println(lastLocation.toString())
                setUpMap()
            }
        }

        createLocationRequest()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.getUiSettings().setZoomControlsEnabled(true)
        setUpMap()
    }

    private fun setUpMap() {
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
            return
        }

        mMap.isMyLocationEnabled = false
        mMap.mapType = GoogleMap.MAP_TYPE_NORMAL

        fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->
            if (location != null) {
                lastLocation = location
                val currentLatLng = LatLng(location.latitude, location.longitude)
                if (!::mPositionMarker.isInitialized) {
                    mPositionMarker = mMap.addMarker(
                        MarkerOptions()
                            .position(currentLatLng)
                            .icon(BitmapDescriptorFactory.fromResource(R.mipmap.maps_location))
                    )
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 17f))
                } else {
                    mPositionMarker.position = currentLatLng
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 17f))

                }

            }
        }

    }

    private fun startLocationUpdates() {

        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
            return
        }
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null)
    }

    private fun createLocationRequest() {
        locationRequest = LocationRequest()
        locationRequest.interval = 1000
        locationRequest.fastestInterval = 1000
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY

        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)

        val client = LocationServices.getSettingsClient(this)
        val task = client.checkLocationSettings(builder.build())

        task.addOnSuccessListener {
            locationUpdateState = true
            startLocationUpdates()
        }
        task.addOnFailureListener { e ->
            if (e is ResolvableApiException) {
                try {
                    e.startResolutionForResult(
                        this@MapsActivity,
                        REQUEST_CHECK_SETTINGS
                    )
                } catch (sendEx: IntentSender.SendIntentException) {
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CHECK_SETTINGS) {
            if (resultCode == Activity.RESULT_OK) {
                locationUpdateState = true
                startLocationUpdates()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    public override fun onResume() {
        super.onResume()
        if (!locationUpdateState) {
            startLocationUpdates()
        }
        timer.start()
    }

    override fun onMarkerClick(p0: Marker?) = false

    inner class Timer(miliis: Long) : CountDownTimer(miliis, 1) {
        var millisUntilFinished: Long = 0
        override fun onFinish() {
        }

        override fun onTick(millisUntilFinished: Long) {
            this.millisUntilFinished = millisUntilFinished
            val resTime = time - millisUntilFinished
            passTime = resTime
            if (travelOn) {
                timeTravel = passTime
                updateTimeTravel()
            }
            if (warningOn) {
                checkTimeWarning()
            }
        }
    }

    fun updateTimeTravel() {

        var tvTimeTravel: TextView = findViewById(R.id.tv_time_travel)

        val f = DecimalFormat("00")
        val hour = f.format(timeTravel / 3600000 % 24)
        val min = f.format(timeTravel / 60000 % 60)
        val sec = f.format(timeTravel / 1000 % 60)

        var blickDot = sec.toDouble().rem(2)
        if (blickDot == 0.0) {
            tvTimeTravel.text = "$hour:$min"
        } else {
            tvTimeTravel.text = "$hour $min"
        }
    }

    fun pauseTravel(v: View) {
        val divModal: ConstraintLayout = findViewById(R.id.div_modal)
        val tvModal: TextView = findViewById(R.id.tv_modal)
        val btModal: Button = findViewById(R.id.bt_modal)

        val tvModPause = "Viagem pausada"
        val btModPause = "RETORNAR VIAGEM"

        tvModal.text = tvModPause
        btModal.text = btModPause

        onPause()
        visibilityModal(divModal)
    }

    fun closeTravel(v: View) {
        val divModal: ConstraintLayout = findViewById(R.id.div_modal)
        val tvModal: TextView = findViewById(R.id.tv_modal)
        val btModal: Button = findViewById(R.id.bt_modal)

        val tvModClose = "Finalizar a viagem?"
        val btModClose = "ENCERRAR VIAGEM"

        tvModal.text = tvModClose
        btModal.text = btModClose

        onPause()
        visibilityModal(divModal)
    }

    fun btnModal(v: View) {
        val divModal: ConstraintLayout = findViewById(R.id.div_modal)
        val tvModal: TextView = findViewById(R.id.tv_modal)

        if (tvModal.text == "RETORNAR VIAGEM") {
            onResume()
        } else {
            println("Redirecionar")
        }
        onResume()
        visibilityModal(divModal)
    }

    fun visibilityModal(m: ConstraintLayout) {
        if (m.visibility == View.VISIBLE) {
            m.animate()
                .translationZ(1f)
                .alpha(0f)
                .setDuration(200.toLong())
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        m.visibility = View.GONE
                    }
                })
        } else {
            m.animate()
                .translationZ(0f)
                .alpha(1f)
                .setDuration(200.toLong())
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationStart(animation: Animator) {
                        m.visibility = View.VISIBLE
                    }
                })
        }
    }

    fun warningTravel(v: View) {
        val divWar: ConstraintLayout = findViewById(R.id.div_war)

        if (divWar.visibility == View.VISIBLE) {
            divWar.animate()
                .translationY(divWar.height.toFloat())
                .alpha(0f)
                .setDuration(500L)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        divWar.visibility = View.GONE
                    }
                })
            onResume()
        } else {
            divWar.animate()
                .alpha(1f)
                .translationY(0f)
                .setDuration(500L)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationStart(animation: Animator) {
                        divWar.visibility = View.VISIBLE
                    }
                })
            onPause()
        }
    }

    fun startWarning(v: View) {

        val divWar: ConstraintLayout = findViewById(R.id.div_war)
        val divWarFinal: ConstraintLayout = findViewById(R.id.div_war_final)

        if (v.id == R.id.div_war_cart1) {
            numberWarning = "193"
        } else if (v.id == R.id.div_war_cart2) {
            numberWarning = "190"
        } else if (v.id == R.id.div_war_cart3) {
            numberWarning = "+5511954321489"
        }
        divWar.visibility = View.GONE
        divWarFinal.visibility = View.VISIBLE
        warningOn = true
    }

    fun stopWarning(v: View) {
        val divWarFinal: ConstraintLayout = findViewById(R.id.div_war_final)
        divWarFinal.visibility = View.GONE
        warningOn = false
        timeWarning = 0
        initTimeWarning = 0
    }

    fun checkTimeWarning() {
        val divWarFinal: ConstraintLayout = findViewById(R.id.div_war_final)
        val tvWarTimeCancel: TextView = findViewById(R.id.tv_war_time_cancel)
        val f = DecimalFormat("00")

        if(initTimeWarning == 0L) {
            initTimeWarning = passTime
        }
        timeWarning = passTime - initTimeWarning

        val sec = f.format(timeWarning / 1000 % 60)

        val timeDisplay = 10 - sec.toInt()
        println("\n\n\n timeDisplay: $timeDisplay")
        println("\n\n\n timeWarning: $timeWarning")
        if(timeDisplay >= 0) {
            tvWarTimeCancel.text = " $timeDisplay "
        } else {
            warningOn = false
            timeWarning = 0
            initTimeWarning = 0
            divWarFinal.visibility = View.GONE
            runWarningCall()
        }
    }

    fun runWarningCall() {
        val callIntent: Intent = Uri.parse("tel:$numberWarning").let { number ->
            Intent(Intent.ACTION_DIAL, number)
        }
        startActivity(callIntent)
    }


}


