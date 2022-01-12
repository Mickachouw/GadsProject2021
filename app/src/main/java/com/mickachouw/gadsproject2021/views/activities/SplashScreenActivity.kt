package com.mickachouw.gadsproject2021.views.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.mickachouw.gadsproject2021.R
import com.mickachouw.gadsproject2021.databinding.ActivitySplashScreenBinding

// TODO : Use Jetpack SplashScreen Library

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private var binding: ActivitySplashScreenBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash_screen)

        // In Activity's onCreate() for instance
        // To make the notification bar transparent.
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        Handler(Looper.myLooper()!!).postDelayed({
            startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
            finish()
        }, 2000)
    }
}