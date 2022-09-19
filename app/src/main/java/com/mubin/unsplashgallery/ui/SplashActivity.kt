package com.mubin.unsplashgallery.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.fondesa.kpermissions.*
import com.fondesa.kpermissions.extension.permissionsBuilder
import com.fondesa.kpermissions.extension.send
import com.mubin.unsplashgallery.databinding.ActivitySplashBinding
import com.mubin.unsplashgallery.helper.Session

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    private var isGranted: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onResume() {
        super.onResume()
        Handler(Looper.getMainLooper()).postDelayed({
            if (Session.isFirstVisit){
                checkConnectivity()
            } else {
                startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
                finish()
            }

        }, 1000)
    }

    private fun checkConnectivity() {
        if (isNetworkAvailable(applicationContext) && askForPermission()){
            Session.isFirstVisit = false
            startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
            finish()
        } else {
            Toast.makeText(this, "Internet Connection is Required", Toast.LENGTH_SHORT).show()
            startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
        }
    }

    private fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val nw = connectivityManager.activeNetwork ?: return false
            val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
            return when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
                else -> false
            }
        } else {
            return connectivityManager.activeNetworkInfo?.isConnected ?: false
        }
    }

    private fun askForPermission(): Boolean {

        permissionsBuilder(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO).build()
            .send { result ->
                when {
                    result.allGranted() -> {
                        isGranted = true
                    }
                    result.allDenied() || result.anyDenied() -> {

                    }

                    result.allPermanentlyDenied() || result.anyPermanentlyDenied() -> {

                    }
                }
            }
        return isGranted
    }


}