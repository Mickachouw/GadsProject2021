package com.mickachouw.gadsproject2021.data.remote

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class NetworkUtils {

        companion object {
            const val URL_IMAGE = "https://s3.eu-central-1.amazonaws.com/bbxt-static-icons/type-id/png_512"
        }

        fun getNetworkStatus(context: Context): LiveData<Boolean> {
            val isAvailableLiveData = MutableLiveData<Boolean>()
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkRequest = NetworkRequest.Builder()

            connectivityManager.registerNetworkCallback(networkRequest.build(), object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    isAvailableLiveData.postValue(true)
                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    isAvailableLiveData.postValue(false)

                }
            })
            return isAvailableLiveData
        }
    }