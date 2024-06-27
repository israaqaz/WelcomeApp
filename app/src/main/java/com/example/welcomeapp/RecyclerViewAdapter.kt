package com.example.welcomeapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(private val contactNames: List<Contact>,
                          private val clickListener:(Contact)->Unit
                            ) : RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.list_item,parent,false)
        return MyViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val contact = contactNames[position]
        holder.bind(contact,clickListener)

    }
    override fun getItemCount(): Int {
        return contactNames.size
    }


}

class MyViewHolder(val view : View):RecyclerView.ViewHolder(view){
    fun bind(contact: Contact,clickListener:(Contact)->Unit) {
        val contactName = view.findViewById<TextView>(R.id.tvName)
        contactName.text = contact.name

        view.setOnClickListener{
            clickListener(contact)
        }
    }
}