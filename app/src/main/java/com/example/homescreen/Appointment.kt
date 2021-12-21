package com.example.homescreen

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homescreen.databinding.ActivityAppointmentBinding

class Appointment : AppCompatActivity() {
    private lateinit var binding: ActivityAppointmentBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAppointmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar = supportActionBar

        actionBar!!.title = "Appointment"

        actionBar.setDisplayHomeAsUpEnabled(false)

        //Get hospitals spinner reference and give Toast when we select and item
        binding.spHospitals.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position>0){
                    binding.spDoctors.isEnabled = true
                }
                else {
                    binding.spDoctors.isEnabled = false
                    binding.spDates.isEnabled = false
                    binding.scheduleButton.isEnabled = false
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

        binding.spDoctors.isEnabled = false
        //Get doctors spinner reference and give Toast when we select and item
        binding.spDoctors.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position>0){
                    binding.spDates.isEnabled = true
                }
                else {
                    binding.spDates.isEnabled = false
                    binding.scheduleButton.isEnabled = false
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

        binding.spDates.isEnabled = false
        //Get dates spinner reference and give Toast when we select and item
        binding.spDates.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                binding.scheduleButton.isEnabled = position>0
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }


        //Schedule an appointment when you press this button
        binding.scheduleButton.setOnClickListener {
            Toast.makeText(this, "Appointment scheduled successfully!", Toast.LENGTH_LONG).show()

        }


    }
}