package com.example.welcomeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.welcomeapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var fragmentManager: FragmentManager
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.i("MyTag", "Main Activity")

        setSupportActionBar(binding.toolbar)

        if (savedInstanceState == null) {
            goToFragment(HomeFragment(), "Home")
        }

        binding.bottomNavigationBar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> goToFragment(HomeFragment(), "Home")
                R.id.records -> goToFragment(RecoedsFragment(), "Records")
                R.id.settings -> goToFragment(SettingsFragment(), "Settings")
                else -> {}
            }
            true
        }
    }


    private fun goToFragment(fragment: Fragment, title: String){
        fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, fragment)
            .commit()

        supportActionBar?.title = title
    }
}