package com.example.welcomeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Website.URL
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    private lateinit var userDataTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("MyTag","Main Activity")
        val greetingTextView = findViewById<TextView>(R.id.tvWelcome)
        val inputField = findViewById<EditText>(R.id.etUsername)
        val submitButton = findViewById<Button>(R.id.btnSubmit)
        val nextButton = findViewById<Button>(R.id.btnNext)
       // userDataTextView = findViewById(R.id.userDataTextView)
        
        submitButton.setOnClickListener {
            val enteredName = inputField.text.toString()
            if (enteredName == ""){
                greetingTextView.text = "Welcome ....."
                Toast.makeText(
                    this@MainActivity,
                    "Please enter your name",
                    Toast.LENGTH_SHORT).show()}
            else {
                val message = "Welcome $enteredName"
                greetingTextView.text = message
            }

           // fetchUserData()
        }
        nextButton.setOnClickListener {
            val intentUserName = Intent (this, SecondActivity::class.java)
            intentUserName.putExtra("username",inputField.text.toString())
            startActivity(intentUserName)
        }

    }

    /*private fun fetchUserData() {
        CoroutineScope(Dispatchers.IO).launch {
            val url = URL("https://reqres.in/api/users?page=2")
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod ="GET"

            val responseCode = connection.responseCode
            val responseMessage = connection.responseMessage

            if (responseCode == HttpURLConnection.HTTP_OK) {
                val response = connection.inputStream.bufferedReader().use { it.readText()}
                val users = JSONArray(response)
                val userData = StringBuilder()

                for (i in 0 until users.length()){
                    val user = users.getJSONObject(i)
                    val firstName = user.getString("first_name")
                    val lastName = user.getString("last_name")
                    val email = user.getString("email")
                    userData.append("Name: $firstName $lastName\n Email: $email\n\n")
                }

                withContext(Dispatchers.IO) {
                    userDataTextView.text = userData.toString()
                }
            } else {
                withContext(Dispatchers.IO) {
                    userDataTextView.text = "Failed to fetch data: $responseMessage"
                }
            }
            connection.disconnect()
        }
    }*/

}