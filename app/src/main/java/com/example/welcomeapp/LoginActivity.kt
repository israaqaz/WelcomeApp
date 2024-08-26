package com.example.welcomeapp
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.Manifest


class LoginActivity : AppCompatActivity() {

    private lateinit var username:EditText
    private lateinit var userEmail:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Log.i("MyTag","Login Activity on Create")

        SharedPrefrencesSingleton.init(this)

        if (SharedPrefrencesSingleton.isLoggedIn()) {
            val intentLogin = Intent(this, MainActivity::class.java)
            startActivity(intentLogin)
            finish()
            return
        }

        username = findViewById(R.id.usernameET)
        userEmail = findViewById(R.id.etUserEmail)
        val userPassword = findViewById<EditText>(R.id.etUserPassword)
        val logInbtn = findViewById<Button>(R.id.btnLogin)
        val registerbtn = findViewById<Button>(R.id.btnRegister)


        logInbtn.setOnClickListener {
            val usernameName = username.text.toString().trim()
            val useremailEmail = userEmail.text.toString().trim()
            val userPassword = userPassword.text.toString()


            if (usernameName.isNotEmpty() && useremailEmail.isNotEmpty() && userPassword.isNotEmpty()) {

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

