package com.wix.unicorn.feature.auth

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.wix.network.NetworkHandler
import com.wix.unicorn.R
import org.koin.android.ext.android.inject


class SplashActivity : AppCompatActivity() {

    val networkHendler: NetworkHandler by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(this, "${networkHendler.isConnected}", Toast.LENGTH_LONG).show()
    }
}
