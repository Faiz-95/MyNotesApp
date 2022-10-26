package com.example.mynotesapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class SignupActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //sets the layout file to activity_splash_screen
        setContentView(R.layout.activity_signup)
        //hides the status bar when splash screen is displayed
        supportActionBar?.hide()

        val signup = findViewById<Button>(R.id.signupButton)

        val emailTextView = findViewById<EditText>(R.id.signupEmailTextView)
        val passwordTextView = findViewById<EditText>(R.id.signupPasswordTextView)

        // Initialize Firebase Auth
        auth = Firebase.auth
        auth = FirebaseAuth.getInstance()
        var currentUser = auth.currentUser

        signup.setOnClickListener {
            val email = emailTextView.text.toString()
            val password = passwordTextView.text.toString()

            var checkedEmail = checkEmail(email)
            var checkedPassword = checkPassword(password)

            if (checkedEmail and checkedPassword) {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            Toast.makeText(this, "User successfully created", Toast.LENGTH_SHORT)
                                .show() //create a toast
                            Log.e("NOTE_ME", "New user successfully created") //create a log entry
                        } else {
                            Toast.makeText(this, "User not created", Toast.LENGTH_SHORT)
                                .show() //create a toast
                            Log.e("NOTE_ME", "New user not created") //create a log entry
                        }
                    }
            }
        }
    }

    private fun checkPassword(password: String): Boolean {
        val passwordPattern = Regex("[^A-Za-z]")
        if (password.isEmpty()){
            Toast.makeText(this, "Please enter a password", Toast.LENGTH_SHORT).show() //create a toast
            Log.e("NOTE_ME", "No password entered") //create a log entry
            return false
        }
        else if (password.length < 8){
            Toast.makeText(this, "Please enter a longer password", Toast.LENGTH_SHORT).show() //create a toast
            Log.e("NOTE_ME", "Short password") //create a log entry
            return false
        }
        else {
            return true
        }
    }

    private fun checkEmail(email: String): Boolean {
        if (email.isEmpty()){
            Toast.makeText(this, "Please enter an email", Toast.LENGTH_SHORT).show() //create a toast
            Log.e("NOTE_ME", "No email entered") //create a log entry
            return false
        }
        else {
            return true
        }
    }
}