package com.example.welcomeapp

import android.app.Application

class WelcomeApp : Application() {

    override fun onCreate() {
        super.onCreate()
// Initialize SharedPreferencesManager with application context, ensuring that it is ready to use when the application starts.
        SharedPrefrencesSingleton.init(this)

    }

}