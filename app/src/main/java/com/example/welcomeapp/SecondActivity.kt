package com.example.welcomeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val userName = findViewById<TextView>(R.id.tvUsernametxt)
        userName.text = intent.getStringExtra("username")


    }
}