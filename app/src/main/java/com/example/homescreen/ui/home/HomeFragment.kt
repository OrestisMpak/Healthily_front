package com.example.homescreen.ui.home

import com.example.homescreen.RecyclerAdapterHome
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homescreen.*
import com.example.homescreen.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.activity_appointment.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    lateinit var layoutManager: RecyclerView.LayoutManager
    //lateinit var adapter: RecyclerView.Adapter<RecyclerAdapterHome.ViewHolder>
    lateinit var adapter1: RecyclerView.Adapter<RecycleAdapterAppointment.ViewHolder>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //Welcome
        layoutManager = LinearLayoutManager(this@HomeFragment.context)

        binding.recyclerViewHome.layoutManager = layoutManager

        //adapter = RecyclerAdapterHome()
        //binding.recyclerViewHome.adapter = adapter

        //Diagnoses Button
        binding.DiagnosesBtn.setOnClickListener {
            val intent = Intent (this@HomeFragment.requireContext(), Diagnoses::class.java)
            startActivity(intent)
        }

        //Prescription Button
        binding.PrescriptionsBtn.setOnClickListener {
            val intent = Intent (this@HomeFragment.requireContext(), Prescriptions::class.java)
            startActivity(intent)
        }

        //Appointment Button
        binding.AppointmentBtn.setOnClickListener {
            val intent = Intent (this@HomeFragment.requireContext(), Appointment::class.java)
            startActivity(intent)
        }

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}