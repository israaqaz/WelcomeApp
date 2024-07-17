package com.example.welcomeapp

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class UserData (
    val data: List<Contact>
)

@JsonClass(generateAdapter = true)
data class Contact(val id: Int,
                   val email: String,
                   val first_name: String,
                   val last_name: String,
                   val avatar: String)
