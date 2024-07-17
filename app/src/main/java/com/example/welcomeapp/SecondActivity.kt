package com.example.welcomeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.moshi.Moshi
import java.net.HttpURLConnection
import kotlinx.coroutines.*
import java.net.URL

class SecondActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecyclerViewAdapter
    private lateinit var contacts: List<Contact>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("MyTag", "Second Activity")
        setContentView(R.layout.activity_second)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        fetchUserData()
    }


    private fun fetchUserData() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val url = URL("https://reqres.in/api/users?page=2")
                val connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "GET"
                connection.connect()

                val inputStream = connection.inputStream
                val json = inputStream.bufferedReader().use { it.readText() }
                val moshi = Moshi.Builder().build()
                val jsonAdapter = moshi.adapter(UserData::class.java)
                val userResponse = jsonAdapter.fromJson(json)

                if (userResponse != null) {
                    contacts = userResponse.data
                    withContext(Dispatchers.Main) {
                        adapter = RecyclerViewAdapter(contacts)
                        recyclerView.adapter = adapter
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

