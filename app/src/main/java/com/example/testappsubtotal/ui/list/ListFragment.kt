package com.example.testappsubtotal.ui.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testappsubtotal.R
import com.example.testappsubtotal.databinding.FragmentListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

class ListFragment : Fragment(R.layout.fragment_list) {

    private var _binding: FragmentListBinding? = null
    private val binding: FragmentListBinding get() = _binding!!
    lateinit var viewModel: ListViewModel
    private val booksAdapter by lazy {
        BooksAdapter {
            navigateToBooksDetails(id = "")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[ListViewModel::class.java]
        _binding = FragmentListBinding.bind(view)
        initView()
        initObservers()
    }

    private fun initView() {
        binding.rvResults.apply {
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL, false
            )
            adapter = booksAdapter
        }
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getBooksList().observe(viewLifecycleOwner) {
                booksAdapter.submitData(lifecycle, it)
                Log.d("develop", "1")
                Log.d("develop", "adapter: ${booksAdapter.snapshot().items}")
            }
        }
    }

    private fun subscribeUi() {
        viewModel.bookList.observe(viewLifecycleOwner) {
            Log.d("develop", "list: $it")
        }
    }

    private fun navigateToBooksDetails(id: String) {
        findNavController().navigate(R.id.action_listFragment_to_detailsFragment)
    }


}
