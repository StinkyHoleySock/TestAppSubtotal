package com.example.testappsubtotal.utils

import android.annotation.TargetApi
import android.content.Context
import android.net.*
import android.os.Build
import androidx.lifecycle.LiveData

class NetworkConnection(context: Context): LiveData<Boolean>()  {

    private var connectivityManager: ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private lateinit var networkCallback: ConnectivityManager.NetworkCallback

    override fun onActive() {
        super.onActive()
        updateConnection()
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> {
                connectivityManager.registerDefaultNetworkCallback(connectivityManagerCallback())
            }
            else -> {
                lollipopNetworkRequest()
            }
        }
    }

    override fun onInactive() {
        super.onInactive()
        connectivityManager.unregisterNetworkCallback(connectivityManagerCallback())
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun lollipopNetworkRequest() {
        val requestBuilder = NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_ETHERNET)

        connectivityManager.registerNetworkCallback(
            requestBuilder.build(),
            connectivityManagerCallback()
        )
    }


    private fun connectivityManagerCallback(): ConnectivityManager.NetworkCallback {
        networkCallback = object : ConnectivityManager.NetworkCallback() {

            override fun onLost(network: Network) {
                super.onLost(network)
                postValue(false)
            }

            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                postValue(true)
            }
        }
        return networkCallback
    }

    private fun updateConnection() {
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        postValue(activeNetwork?.isConnected == true)
    }
}