package com.example.welcomeapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request


class RecordsFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecyclerViewAdapter
    private lateinit var contacts: List<Contact>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_records, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        fetchUserData()
    }

    private fun fetchUserData() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val client = OkHttpClient()
                val request = Request.Builder().url("https://reqres.in/api/users?page=2").build()
                val response = client.newCall(request).execute()
                val responseBody = response.body?.string()

                if (responseBody != null) {
                    val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                    val jsonAdapter = moshi.adapter(UserData::class.java)
                    val userData = jsonAdapter.fromJson(responseBody)

                    if (userData != null) {
                        contacts = userData.data
                        withContext(Dispatchers.Main) {
                            adapter = RecyclerViewAdapter(contacts)
                            recyclerView.adapter = adapter
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

