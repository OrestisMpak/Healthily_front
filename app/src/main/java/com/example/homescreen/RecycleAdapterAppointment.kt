package com.example.homescreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecycleAdapterAppointment: RecyclerView.Adapter<RecycleAdapterAppointment.ViewHolder>() {

    val hospital = arrayOf("AHEPA University Hospital")

    val doctor = arrayOf("Dentist")

    val date = arrayOf("30/11 11:30")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecycleAdapterAppointment.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_layout_appointment, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecycleAdapterAppointment.ViewHolder, position: Int) {
        holder.itemHospital.text = hospital[position]
        holder.itemDoctor.text = doctor[position]
        holder.itemDate.text = date[position]
    }

    override fun getItemCount(): Int {
        return hospital.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var itemHospital: TextView
        var itemDoctor: TextView
        var itemDate: TextView

        init{
            itemHospital = itemView.findViewById(R.id.item_hospital)
            itemDoctor = itemView.findViewById(R.id.item_doctor)
            itemDate = itemView.findViewById(R.id.item_date)

        }

    }
}