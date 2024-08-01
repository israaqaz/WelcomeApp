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
    private const val KEY_LOGGED_IN = "logged_in"


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
            putBoolean(KEY_LOGGED_IN,true)
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

    fun isLoggedIn(): Boolean {
        return preferences.getBoolean(KEY_LOGGED_IN, false)
    }

    fun login() {
        preferences.edit().putBoolean(KEY_LOGGED_IN, true).apply()
    }

    fun logout() {
        val editor = preferences.edit()
        editor.putBoolean(KEY_LOGGED_IN, false)
       // editor.clear()
        editor.apply()
    }

    // to check if email is already registered
    fun isEmailRegistered(email: String): Boolean {
        return preferences.getString(KEY_EMAIL, null) == email

    }




// i can add more functions for any other data types:
// (int, float, char, boolean, Enumerated, Array, Date, Time, Datetime, Timestamp)

}