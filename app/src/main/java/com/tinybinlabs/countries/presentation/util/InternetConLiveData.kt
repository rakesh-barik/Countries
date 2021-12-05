package com.tinybinlabs.countries.presentation.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.util.Log
import androidx.lifecycle.LiveData

const val TAG: String = "InternetConLiveData"

class InternetConLiveData(context: Context) : LiveData<Boolean>() {

    private val connectivityManager = context.getSystemService(
        Context.CONNECTIVITY_SERVICE
    ) as ConnectivityManager

    override fun onActive() {
        super.onActive()
        Log.d(TAG, "onActive")
        val networkRequest =
            NetworkRequest.Builder()
                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .build()
        connectivityManager.registerNetworkCallback(networkRequest, provideNetworkCallback())
    }

    override fun onInactive() {
        super.onInactive()
        try {
            connectivityManager.unregisterNetworkCallback(provideNetworkCallback())
        } catch (illegalArgException: IllegalArgumentException) {
            Log.w(TAG, "${illegalArgException.stackTrace}")
        }

    }

    private fun provideNetworkCallback() = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            postValue(hasInternetConnectivity())
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            postValue(hasInternetConnectivity())
        }

    }

    private fun hasInternetConnectivity(): Boolean {

        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false

        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
    }
}