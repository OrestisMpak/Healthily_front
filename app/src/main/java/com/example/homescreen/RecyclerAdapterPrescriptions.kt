package com.example.homescreen

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapterPrescriptions(val context: Context, val prescriptionsList: List<PrescriptionsResponse>): RecyclerView.Adapter<RecyclerAdapterPrescriptions.ViewHolder>() {
    //val titles = arrayOf("Chapter One", "Chapter Two", "Chapter Three", "Chapter Four", "Chapter Five", "Chapter Six", "Chapter Seven", "Chapter Eight")

    //val details = arrayOf("Item one details", "Item two details", "Item three details", "Item four details", "Item five details", "Item six details", "Item seven details", "Item eight details")

    //val years = arrayOf("Item one details", "Item two details", "Item three details", "Item four details", "Item five details", "Item six details", "Item seven details", "Item eight details")

    //val images = intArrayOf(R.drawable.prescriptions, R.drawable.prescriptions, R.drawable.prescriptions, R.drawable.prescriptions, R.drawable.prescriptions, R.drawable.prescriptions, R.drawable.prescriptions, R.drawable.prescriptions)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_layout_diagnoses, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemTitle.text = prescriptionsList[position].userMedicine
        holder.itemDetail.text = prescriptionsList[position].userDoctor
        holder.itemYear.text = prescriptionsList[position].userDate
        holder.itemImage.setImageResource(R.drawable.prescriptions)
    }

    override fun getItemCount(): Int {
        return prescriptionsList.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var itemImage: ImageView
        var itemTitle: TextView
        var itemDetail: TextView
        var itemYear: TextView

        init{
            itemImage = itemView.findViewById(R.id.item_image)
            itemTitle = itemView.findViewById(R.id.item_title)
            itemDetail = itemView.findViewById(R.id.item_detail)
            itemYear = itemView.findViewById(R.id.item_year)

            itemView.setOnClickListener{
                val position: Int = adapterPosition

                Toast.makeText(itemView.context, "you clicked on ${prescriptionsList[position]}", Toast.LENGTH_LONG).show()
            }
        }

    }

}