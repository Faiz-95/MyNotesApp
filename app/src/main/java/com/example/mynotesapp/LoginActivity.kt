package com.example.mynotesapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //sets the layout file to activity_splash_screen
        setContentView(R.layout.activity_login)

        //hides the status bar when splash screen is displayed
        supportActionBar?.hide()

        val login = findViewById<Button>(R.id.loginButton)
        val signup = findViewById<TextView>(R.id.newUserTextContd)

        signup.setOnClickListener{
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
    }
}