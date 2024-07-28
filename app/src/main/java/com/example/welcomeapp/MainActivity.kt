package com.example.welcomeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    private lateinit var userDataTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("MyTag","Main Activity")

        val greetingTextView = findViewById<TextView>(R.id.tvWelcome)
        val nextButton = findViewById<Button>(R.id.btnNext)
        val logoutButton = findViewById<Button>(R.id.btnLogout)

        // Get the username from SharedPreferences
        val username = SharedPrefrencesSingleton.getUsername()
        val message = "Welcome $username"
        greetingTextView.text = message


        nextButton.setOnClickListener {
            val intentUserName = Intent (this, SecondActivity::class.java)
            startActivity(intentUserName)
        }

        logoutButton.setOnClickListener {
            // Clear login status
            SharedPrefrencesSingleton.logout()

            // Redirect to LoginActivity
            val intentLogout = Intent(this, LoginActivity::class.java)
            startActivity(intentLogout)
            finish() // Finish MainActivity to prevent the user from coming back here with the back button
        }
    }

}