package br.com.iotruck.mobino.commons.network

import android.content.Context
import android.net.ConnectivityManager

class NetworkStatus {

    companion object {

        fun isConnected(context: Context): Boolean {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (cm != null) {
                val ni = cm.activeNetworkInfo
                return ni != null && ni.isConnected
            }
            return false
        }

    }

}