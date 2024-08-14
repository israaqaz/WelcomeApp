package com.example.welcomeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.welcomeapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var fragmentManager: FragmentManager
    private lateinit var binding: ActivityMainBinding
    private lateinit var logoutButton: Button
    private lateinit var greetingTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.i("MyTag","Main Activity")

        logoutButton = findViewById(R.id.btnLogout)
        greetingTextView = findViewById(R.id.tvWelcome)

        // Get the username from SharedPreferences
        val username = SharedPrefrencesSingleton.getUsername()
        val message = "Welcome $username"
        greetingTextView.text = message

        binding.bottomNavigationBar.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> goToFragment(HomeFragment())
                R.id.profile -> goToFragment(ProfileFragment())
                R.id.settings -> goToFragment(SettingsFragment())

            else ->{
                
            }
        }
            
            true
        }

        binding.btnRecords.setOnClickListener {
            goToFragment(RecordsFragment())
        }

        logoutButton.setOnClickListener {
            // Clear login status
            SharedPrefrencesSingleton.logout()

            // Redirect to LoginActivity
            val intentLogout = Intent(this, LoginActivity::class.java)
            startActivity(intentLogout)
            this?.finish() // Finish MainActivity to prevent the user from coming back here with the back button
        }
    }

    private fun goToFragment(fragment: Fragment){
        fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.fragmentContainerView, fragment).commit()
    }
}