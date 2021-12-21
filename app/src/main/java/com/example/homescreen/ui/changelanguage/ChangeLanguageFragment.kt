package com.example.homescreen.ui.changelanguage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.homescreen.databinding.FragmentChangeLanguageBinding
import com.example.homescreen.databinding.FragmentLogoutBinding

class ChangeLanguageFragment : Fragment() {

    private var _binding: FragmentChangeLanguageBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChangeLanguageBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textChangelanguage
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}