package com.wix.network

import android.content.Context
import android.net.ConnectivityManager

class NetworkHandler(private val context: Context) {
    val isConnected: Boolean
        get() = (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
            .activeNetworkInfo
            .isConnected
}