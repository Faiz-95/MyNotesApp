package com.example.mynotesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //sets the layout file to activity_splash_screen
        setContentView(R.layout.activity_splash_screen)

        //hides the status bar when splash screen is displayed
        supportActionBar?.hide()

        //displays the splash screen for 1.5 seconds and then sends user to MainActivity
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            startActivity(Intent(this, MainActivity::class.java))
        }, 1500)
    }
}