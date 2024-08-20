package com.example.welcomeapp

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private lateinit var logoutButton: Button
    private lateinit var bluetoothStatus: TextView
    private lateinit var locationStatus: TextView
    private lateinit var btnBluetooth: Button
    private lateinit var btnLocation: Button


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        logoutButton = view.findViewById(R.id.btnLogout)
        bluetoothStatus = view.findViewById(R.id.bluetoothStatus)
        locationStatus = view.findViewById(R.id.locationStatus)
        btnBluetooth = view.findViewById(R.id.btnBluetooth)
        btnLocation = view.findViewById(R.id.btnLocation)

        updatePermissionStatus()

        logoutButton.setOnClickListener {
            SharedPrefrencesSingleton.logout()
            val intentLogout = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intentLogout)
            requireActivity().finish()
        }

        btnBluetooth.setOnClickListener {
            openDeviceSettings()
        }

        btnLocation.setOnClickListener {
            openDeviceSettings()
        }

    }

    private fun updatePermissionStatus() {
        if (checkBluetoothPermissions()) {
            bluetoothStatus.text = "Allowed"
        } else {
            bluetoothStatus.text = "Not Allowed"
        }

        if (checkLocationPermissions()) {
            locationStatus.text = "Allowed"
        } else {
            locationStatus.text = "Not Allowed"
        }
    }

    private fun openDeviceSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri: Uri = Uri.fromParts("package", requireActivity().packageName, null)
        intent.data = uri
        startActivity(intent)
    }

    private fun checkBluetoothPermissions(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            ContextCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.BLUETOOTH_CONNECT
            ) == PackageManager.PERMISSION_GRANTED
        } else {
            true
        }
    }

    private fun checkLocationPermissions(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    override fun onResume() {
        super.onResume()
        updatePermissionStatus()
    }

}