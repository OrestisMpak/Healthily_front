package com.example.homescreen.ui.logout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.homescreen.Login
import com.example.homescreen.databinding.ActivityLoginBinding
import com.example.homescreen.databinding.FragmentHomeBinding
import com.example.homescreen.databinding.FragmentLogoutBinding
import com.example.homescreen.ui.home.HomeViewModel

class LogoutFragment : Fragment() {

    private var _binding: FragmentLogoutBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLogoutBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textLogout
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}