package com.example.testappsubtotal.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.testappsubtotal.R
import com.example.testappsubtotal.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private val args: DetailsFragmentArgs by navArgs()

    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailsBinding.bind(view)
        initView()
    }

    private fun initView() {
        with(binding) {
            tvTitle.text = args.title
            tvDescription.text = args.description
            tvAuthor.text = args.authors
            tvDate.text = args.date

            Glide.with(ivBook.context)
                .load(args.imageLink)
                .error(R.drawable.ic_empty_data)
                .into(ivBook)
        }
    }


}