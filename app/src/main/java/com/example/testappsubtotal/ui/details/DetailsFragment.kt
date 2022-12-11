package com.example.testappsubtotal.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.testappsubtotal.R
import com.example.testappsubtotal.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailsBinding.bind(view)

    }
}