package com.example.welcomeapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SecondActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("MyTag","Second Activity")
        setContentView(R.layout.activity_second)
        val contactNames = listOf<Contact>(
            Contact("Israa","qaznilii@ksau-hs.edu.sa","45672"),
            Contact("Abdullah","alqahtania@ksau-hs.edu.sa","45821"),
            Contact("Abdulrahman","qahtaniabd@ksau-hs.edu.sa","45833"),
            Contact("Hussain","alsaylanih@ksau-hs.edu.sa","45752")
        )

        val recycleView = findViewById<RecyclerView>(R.id.recyclerView)
        recycleView.setBackgroundColor(Color.GRAY)
        recycleView.layoutManager = LinearLayoutManager(this)
        recycleView.adapter = RecyclerViewAdapter(
            contactNames,
        ) { selectedContact: Contact ->
            contactInfo(selectedContact)
        }

        val userName = findViewById<TextView>(R.id.tvUsernametxt)
        userName.text = intent.getStringExtra("username")

    }
    private fun contactInfo(contact: Contact){
        Toast.makeText(
            this@SecondActivity,
            "Email: ${contact.email} Number is: ${contact.phoneNumber}",
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onStart() {
        super.onStart()
        Log.i("MyTag","Second Activity onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("MyTag","Second Activity onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("MyTag","Second Activity onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("MyTag","Second Activity onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("MyTag","Second Activity onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("MyTag","Second Activity onRestart")
    }
}