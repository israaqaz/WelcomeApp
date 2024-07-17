package com.example.welcomeapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecyclerViewAdapter(private val contacts: List<Contact>) : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {
    class MyViewHolder(val view : View):RecyclerView.ViewHolder(view){
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val emailTextView: TextView = itemView.findViewById(R.id.emailTextView)
        val avatarImageView: ImageView = itemView.findViewById(R.id.avatarImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val contact = contacts[position]
        holder.nameTextView.text = "${contact.first_name}  ${contact.last_name}"
        holder.emailTextView.text = contact.email
        Glide.with(holder.avatarImageView.context).load(contact.avatar).into(holder.avatarImageView)
    }
    override fun getItemCount(): Int {
        return contacts.size
    }


}
