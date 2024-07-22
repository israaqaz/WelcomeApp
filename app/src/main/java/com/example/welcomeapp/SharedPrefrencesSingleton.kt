package com.example.welcomeapp

import android.content.Context
import android.content.SharedPreferences

object SharedPrefrencesSingleton {
    private const val PREF_NAME = "mySharedPreferences" //define the name of the shared preferences file
    private const val MODE = Context.MODE_PRIVATE //the mode in which the file accessed
    private lateinit var preferences: SharedPreferences //variable holds the SharedPreferences instance. It is initialized in the init method.

    //List of keys
    private const val KEY_USERNAME = "userCredentials_name"
    private const val KEY_EMAIL = "userCredentials_email"

    // Initializing the SharedPreferences object
    fun init(context: Context){
    preferences = context.getSharedPreferences(PREF_NAME, MODE)
    }

// save and get provided to save and retrieve data from SharedPreferences
// Function to save a string value
    fun saveUserCredentials(username: String, email: String){
        preferences.edit().apply {
            // this function took two parameters and then saved them in (key, valur) arguments
            putString(KEY_USERNAME, username)
            putString(KEY_EMAIL,email)
            apply()
        }
    }

// Function to get a string value
    fun getUsername(): String? {
        return preferences.getString(KEY_USERNAME, null)
    }

    fun getEmail(): String? {
        return preferences.getString(KEY_EMAIL, null)
    }

// i can add more functions for any other data types:
// (int, float, char, boolean, Enumerated, Array, Date, Time, Datetime, Timestamp)


}