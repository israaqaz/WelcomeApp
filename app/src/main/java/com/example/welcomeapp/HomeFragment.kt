package com.example.welcomeapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class HomeFragment : Fragment() {

    private lateinit var logoutButton: Button
    private lateinit var btnShowRecords: Button
    private lateinit var greetingTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        logoutButton = view.findViewById(R.id.btnLogout)
        greetingTextView = view.findViewById(R.id.tvWelcome)
        btnShowRecords = view.findViewById(R.id.btnShowRecords)


        val username = SharedPrefrencesSingleton.getUsername()
        val message = "Welcome $username"
        greetingTextView.text = message

        btnShowRecords.setOnClickListener {
            fragmentManager?.beginTransaction()
                ?.replace(R.id.fragmentContainerView, RecordsFragment())
                ?.commit()

            (activity as? AppCompatActivity)?.supportActionBar?.title = "Records"
        }

        logoutButton.setOnClickListener {

            SharedPrefrencesSingleton.logout()

            val intentLogout = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intentLogout)
            requireActivity().finish()
        }
    }
}