package com.example.welcomeapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity




class RegisterActivity : AppCompatActivity() {
    private lateinit var editTextUsername: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonRegister: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        editTextUsername = findViewById(R.id.editTextUsername)
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        buttonRegister = findViewById(R.id.buttonRegister)

        buttonRegister.setOnClickListener {
            registerUser() }
    }

    private fun registerUser() {
        val username = editTextUsername.text.toString().trim()
        val email = editTextEmail.text.toString().trim()
        val password = editTextPassword.text.toString().trim()

        //username field conditions
        if (username.isEmpty()) {
            editTextUsername.error = "Username is required"
            return
        }
        if (username.length < 5) {
            editTextUsername.error = "Username must be at least 5 characters long"
            return
        }
        if (!username.matches(Regex("^[a-zA-Z0-9]*$"))) {
            editTextUsername.error = "Username can only contain letters and numbers"
            return
        }

        //Email field conditions
        if (email.isEmpty()) {
            editTextEmail.error = "Email is required"
            return
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.error = "Please enter a valid email"
            return
        }

        //Password field conditions
        if (password.isEmpty()) {
            editTextPassword.error = "Password is required"
            return
        }
        if (password.length < 8) {
            editTextPassword.error = "Password must be at least 8 characters long"
            return
        }
        if (!password.matches(Regex("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#!$%^&+=]).{8,}$"))) {
            editTextPassword.error = "Password must include a number, an uppercase letter, a lowercase letter, and a special character"
            return
        }

        if (SharedPrefrencesSingleton.isEmailRegistered(email)) {
            editTextEmail.error = "Email is already registered"
            Toast.makeText(this,"Email is already registered", Toast.LENGTH_SHORT).show()
            return
        }

        // Save user data in SharedPreferences
        SharedPrefrencesSingleton.saveUserCredentials(username, email)

        // Add code to save the user data to the database or authentication server
        Toast.makeText(this, "User registered successfully", Toast.LENGTH_SHORT).show()

        // Navigate to LoginActivity
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}