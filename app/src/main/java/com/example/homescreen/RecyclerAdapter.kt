package com.example.homescreen

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(val context: Context, val diagnosesList: List<DiagnosesResponse>): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    //val titles = arrayOf("Chapter One", "Chapter Two", "Chapter Three", "Chapter Four", "Chapter Five", "Chapter Six", "Chapter Seven", "Chapter Eight")

    //val details = arrayOf("Item one details", "Item two details", "Item three details", "Item four details", "Item five details", "Item six details", "Item seven details", "Item eight details")

    //val years = arrayOf("Item one details", "Item two details", "Item three details", "Item four details", "Item five details", "Item six details", "Item seven details", "Item eight details")

    //val images = intArrayOf(R.drawable.diagnoses_img, R.drawable.diagnoses_img, R.drawable.diagnoses_img, R.drawable.diagnoses_img, R.drawable.diagnoses_img, R.drawable.diagnoses_img, R.drawable.diagnoses_img, R.drawable.diagnoses_img)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_layout_diagnoses, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        holder.itemTitle.text = diagnosesList[position].userDisease
        holder.itemDetail.text = diagnosesList[position].userDoctor
        holder.itemYear.text = diagnosesList[position].userDate
        holder.itemImage.setImageResource(R.drawable.diagnoses_img)
    }

    override fun getItemCount(): Int {
        return diagnosesList.size
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

                Toast.makeText(itemView.context, "you clicked on ${diagnosesList[position]}", Toast.LENGTH_LONG).show()
            }
        }

    }

}