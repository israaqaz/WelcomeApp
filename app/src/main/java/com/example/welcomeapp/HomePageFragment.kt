package com.example.welcomeapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.welcomeapp.databinding.FragmentHomePageBinding



    class HomeFragment : Fragment() {
        private lateinit var greetingTextView: TextView
        private lateinit var nextButton: Button
        private lateinit var logoutButton: Button

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_home_page, container, false)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            Log.i("MyTag", "Home Fragment")

            greetingTextView = view.findViewById(R.id.tvWelcome)
            nextButton = view.findViewById(R.id.btnRecords)
            logoutButton = view.findViewById(R.id.btnLogout)

            // Get the username from SharedPreferences
            val username = SharedPrefrencesSingleton.getUsername()
            val message = "Welcome $username"
            greetingTextView.text = message

            nextButton.setOnClickListener {
                // Navigate to Records Fragment
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, RecordsFragment())
                    .addToBackStack(null)
                    .commit()
            }

            logoutButton.setOnClickListener {
                // Clear login status
                SharedPrefrencesSingleton.logout()

                // Redirect to LoginActivity
                val intentLogout = Intent(activity, LoginActivity::class.java)
                startActivity(intentLogout)
                activity?.finish() // Finish MainActivity to prevent the user from coming back here with the back button
            }
        }
    }