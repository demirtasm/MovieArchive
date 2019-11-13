package com.example.muko.loodos_movearchive.Activities

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.example.muko.loodos_movearchive.R
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.shashank.sony.fancytoastlib.FancyToast
import kotlinx.android.synthetic.main.activity_splash_screen.*


class SplashScreenActivity : AppCompatActivity() {

    var firebaseRemoteConfig: FirebaseRemoteConfig? = null
    private val TAG = "RemoteConfig"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        if (isConnectedToNetwork()) {
            val defaultValue = HashMap<String, Any>()
            val configSettings = FirebaseRemoteConfigSettings.Builder().build()
            firebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
            firebaseRemoteConfig!!.setConfigSettings(configSettings)
            defaultValue["message"] = "DEFAULT TEXT"
            firebaseRemoteConfig!!.setDefaults(defaultValue)
            firebaseRemoteConfig!!.fetch(0)
                .addOnCompleteListener(this@SplashScreenActivity) { task ->
                    if (task.isSuccessful) {
                        firebaseRemoteConfig!!.activateFetched() // Active all value
                        tv_logo.text = firebaseRemoteConfig!!.getString("message")
                        countDownTimer()
                    } else {
                        FancyToast.makeText(this,"" + task.exception!!.message,FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show()
                    }

                }
        } else {
            FancyToast.makeText(this,getString(R.string.check_internet_connection),FancyToast.LENGTH_LONG,FancyToast.WARNING,false).show()
        }
    }

    fun isConnectedToNetwork(): Boolean {

        val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        return connectivityManager?.activeNetworkInfo?.isConnectedOrConnecting() ?: false

    }

    fun countDownTimer() {

        val timer = object : CountDownTimer(1000, 1000) {
            override fun onFinish() {
                var intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
            }

            override fun onTick(millisUntilFinished: Long) {

            }
        }
        timer.start()
    }
}