package com.example.welcomeapp

import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText


class LoginActivity : AppCompatActivity() {

    // we define objects here to call it in multiple functions
    private lateinit var username:EditText // object type should be defined when using lateinit
    private lateinit var userEmail:EditText

    // SharedPreferences objects
    //private lateinit var userCredentials:SharedPreferences
    //private lateinit var editor:SharedPreferences.Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Log.i("MyTag","Login Activity on Create")

        // fields declaring
        username = findViewById(R.id.usernameET)
        userEmail = findViewById(R.id.etUserEmail)
        val userPassword = findViewById<EditText>(R.id.etUserPassword)
        val logInbtn = findViewById<Button>(R.id.btnLogin)
        val registerbtn = findViewById<Button>(R.id.btnRegister)

        // SharedPreferences creating
        //userCredentials = getSharedPreferences("users_info", MODE_PRIVATE) //the name of the folder that will be created in mobile data folder
        //editor = userCredentials.edit() // needed with sharedpreferences to edit the data inside the folder

        logInbtn.setOnClickListener {
            // moving to another activity using intent
                val intentLogin = Intent (this, MainActivity::class.java)
                startActivity(intentLogin)

            }

        registerbtn.setOnClickListener{
            // moving to another activity using intent
                val intentRegister = Intent (this, RegisterActivity::class.java)
                startActivity(intentRegister)
        }
    }

    override fun onPause() {
        super.onPause() // below code will happen when the user go to another activity or have changed application screen
        Log.i("MyTag","Login Activity on Pause")
        //declaring objects to save the data in it
        val usernameName = username.text.toString()
        val useremailEmail = userEmail.text.toString()

        // using sharedpreferences singleton to save data
        SharedPrefrencesSingleton.saveUserCredentials(usernameName,useremailEmail)
        /*
        editor.apply {
            putString("userCredentials_name",usernameName) // preferable to put the folder name then the object name for clear reference
            putString("userCredentials_email",useremailEmail)
            commit() // we need to commit for the action to be made
        }*/
    }

    override fun onResume() {
        super.onResume() // continue the application running interface
        Log.i("MyTag","Login Activity on Resume")
        //using sharedpreferences singleton to retrieve data
        val name = SharedPrefrencesSingleton.getUsername()
        val email = SharedPrefrencesSingleton.getEmail()
        username.setText(name) // defining where to display the data in user interface
        userEmail.setText(email)
        /*
        // bringing back the saved data in shared preferences
        val name = userCredentials.getString("userCredentials_name",null)
        val email = userCredentials.getString("userCredentials_email",null)
        username.setText(name) // defining where to display the data in user interface
        userEmail.setText(email) */
    }
}