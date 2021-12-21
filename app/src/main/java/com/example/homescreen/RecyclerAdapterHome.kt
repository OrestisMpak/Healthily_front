package com.example.homescreen

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapterHome(val context: Context, val fullName: String?, val amka: String?, val email: String?, val homeAddress: String?, val blood: String?): RecyclerView.Adapter<RecyclerAdapterHome.ViewHolder>() {

    val name = arrayOf(fullName)

    val AMKA = arrayOf(amka)

    val blood_type = arrayOf(email)

    val address = arrayOf(homeAddress)

    val phone = arrayOf(blood)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_layout_home, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemName.text = name[position]
        holder.itemAmka.text = AMKA[position]
        holder.itemBloodType.text = blood_type[position]
        holder.itemAddress.text = address[position]
        holder.itemPhone.text = phone[position]
    }

    override fun getItemCount(): Int {
        return name.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var itemName: TextView
        var itemAmka: TextView
        var itemBloodType: TextView
        var itemAddress: TextView
        var itemPhone: TextView

        init{
            itemName = itemView.findViewById(R.id.item_name)
            itemAmka = itemView.findViewById(R.id.item_amka_db)
            itemBloodType = itemView.findViewById(R.id.item_blood_type_db)
            itemAddress = itemView.findViewById(R.id.item_address_db)
            itemPhone = itemView.findViewById(R.id.item_phone_db)

        }

    }
}