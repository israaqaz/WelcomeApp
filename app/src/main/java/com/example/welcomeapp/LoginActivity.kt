package com.example.welcomeapp

import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class LoginActivity : AppCompatActivity() {

    // we define objects here to call it in multiple functions
    private lateinit var username:EditText // object type should be defined when using lateinit
    private lateinit var userEmail:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Log.i("MyTag","Login Activity on Create")

        // Initialize the SharedPreferences
        SharedPrefrencesSingleton.init(this)

        // Check if user is already logged in
        if (SharedPrefrencesSingleton.isLoggedIn()) {
            // User is logged in, redirect to MainActivity
            val intentLogin = Intent(this, MainActivity::class.java)
            startActivity(intentLogin)
            finish()
            return
        }

        // fields declaring
        username = findViewById(R.id.usernameET)
        userEmail = findViewById(R.id.etUserEmail)
        val userPassword = findViewById<EditText>(R.id.etUserPassword)
        val logInbtn = findViewById<Button>(R.id.btnLogin)
        val registerbtn = findViewById<Button>(R.id.btnRegister)


        logInbtn.setOnClickListener {

            //declaring objects to save the data in it
            val usernameName = username.text.toString()
            val useremailEmail = userEmail.text.toString()


            if (usernameName.isNotEmpty() && useremailEmail.isNotEmpty()) {

                if (SharedPrefrencesSingleton.isEmailRegistered(useremailEmail)) {
                    SharedPrefrencesSingleton.login()

                val intentLogin = Intent(this, MainActivity::class.java)
                startActivity(intentLogin)
                finish()
                } else {
                    Toast.makeText(this, "You're not registered, press the register button to register", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please enter your credentials", Toast.LENGTH_SHORT).show()
            }

        }

        registerbtn.setOnClickListener{
            // moving to another activity using intent
                val intentRegister = Intent (this, RegisterActivity::class.java)
                startActivity(intentRegister)
        }
    }


    override fun onResume() {
        super.onResume() // continue the application running interface
        Log.i("MyTag","Login Activity on Resume")
        //using sharedpreferences singleton to retrieve data
        val name = SharedPrefrencesSingleton.getUsername()
        val email = SharedPrefrencesSingleton.getEmail()
        username.setText(name) // defining where to display the data in user interface
        userEmail.setText(email)

    }
}