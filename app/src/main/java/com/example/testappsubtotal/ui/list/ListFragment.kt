package com.example.testappsubtotal.ui.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testappsubtotal.R
import com.example.testappsubtotal.databinding.FragmentListBinding
import com.example.testappsubtotal.model.Books
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

class ListFragment : Fragment(R.layout.fragment_list) {

    private var _binding: FragmentListBinding? = null
    private val binding: FragmentListBinding get() = _binding!!
    lateinit var viewModel: ListViewModel
    private val booksAdapter by lazy {
        BooksAdapter() {
            navigateToBooksDetails()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[ListViewModel::class.java]
        _binding = FragmentListBinding.bind(view)
        initView()
        initSearchView()
        subscribeUi()
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

    private fun initSearchView() {
        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.getBooksList(query)
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.getBooksList(newText)
                return false
            }
        })
    }

    private fun subscribeUi() {
        viewModel.bookList.observe(viewLifecycleOwner) {
            booksAdapter.setData(it.items)
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            Log.d("develop", "isLoading: $isLoading")
            if (isLoading) binding.progressCircular.visibility =
                View.VISIBLE else binding.progressCircular.visibility = View.GONE
        }
    }

    private fun navigateToBooksDetails() {
        findNavController().navigate(R.id.action_listFragment_to_detailsFragment)
    }
}
