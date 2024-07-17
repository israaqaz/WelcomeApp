package com.example.welcomeapp

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText


class LoginActivity : AppCompatActivity() {

    private lateinit var username:EditText
    private lateinit var userEmail:EditText

    // SharedPreferences objects
    private lateinit var userCredentials:SharedPreferences
    private lateinit var editor:SharedPreferences.Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // fields declaring
        username = findViewById(R.id.usernameET)
        userEmail = findViewById(R.id.etUserEmail)
        val userPassword = findViewById<EditText>(R.id.etUserPassword)
        val logInbtn = findViewById<Button>(R.id.btnLogin)
        val registerbtn = findViewById<Button>(R.id.btnRegister)

        // SharedPreferences creating
        userCredentials = getSharedPreferences("users_info", MODE_PRIVATE)
        editor = userCredentials.edit()

        logInbtn.setOnClickListener {
            // moving to another activity using intent
                val intentLogin = Intent (this, MainActivity::class.java)
                startActivity(intentLogin)

            }

        registerbtn.setOnClickListener{
                val intentRegister = Intent (this, RegisterActivity::class.java)
                startActivity(intentRegister)
        }
    }

    override fun onPause() {
        super.onPause()
        val usernameName = username.text.toString()
        val useremailEmail = userEmail.text.toString()
        editor.apply {
            putString("userCredentials_name",usernameName)
            putString("userCredentials_email",useremailEmail)
            commit()
        }
    }

    override fun onResume() {
        super.onResume()
        val name = userCredentials.getString("userCredentials_name",null)
        val email = userCredentials.getString("userCredentials_email",null)
        username.setText(name)
        userEmail.setText(email)
    }
}