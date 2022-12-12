package com.example.testappsubtotal.ui.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.view.get
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
import com.example.testappsubtotal.model.Items
import com.example.testappsubtotal.utils.NetworkConnection
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

class ListFragment : Fragment(R.layout.fragment_list) {

    private var _binding: FragmentListBinding? = null
    private val binding: FragmentListBinding get() = _binding!!
    lateinit var viewModel: ListViewModel
    private val booksAdapter by lazy {
        BooksAdapter() {
            navigateToBooksDetails(it)
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
                val query = getFilter(binding.radioGroup.checkedRadioButtonId)+query
                Log.d("develop", "queryts: $query")
                viewModel.getBooksList(query)
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                val query = getFilter(binding.radioGroup.checkedRadioButtonId)+newText
                Log.d("develop", "querytc: $query")
                viewModel.getBooksList(query)
                return false
            }
        })
    }

    private fun subscribeUi() {
        viewModel.bookList.observe(viewLifecycleOwner) {
            booksAdapter.setData(it.items)
            if (it.items.isEmpty()) binding.tvNoResults.visibility = View.VISIBLE
                else binding.tvNoResults.visibility = View.GONE
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                binding.progressCircular.visibility = View.VISIBLE
                binding.rvResults.visibility = View.INVISIBLE
            } else {
                binding.progressCircular.visibility = View.GONE
                binding.rvResults.visibility = View.VISIBLE
            }
        }

    }

    private fun getFilter(id: Int): String {
        return when (id) {
            R.id.filter_title -> { "intitle:" }
            R.id.filter_author -> { "inauthor:" }
            else -> { "intitle:" }
        }
    }

    private fun navigateToBooksDetails(item: Items) {
        val title = item.volumeInfo?.title ?: "No title"
        val authorsList = item.volumeInfo?.authors
        val authors = authorsList?.joinToString(", ") ?: "No authors"
        val date = item.volumeInfo?.publishedDate ?: "Unknown date"
        val description = item.volumeInfo?.description ?: "No description"
        val imageLink = item.volumeInfo?.imageLinks?.thumbnail ?: ""

        val action = ListFragmentDirections.actionListFragmentToDetailsFragment(
            title, authors, date, imageLink, description
        )
        findNavController().navigate(action)
    }
}
