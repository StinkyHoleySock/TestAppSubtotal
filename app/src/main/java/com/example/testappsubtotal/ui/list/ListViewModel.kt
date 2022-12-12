package com.example.testappsubtotal.ui.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.testappsubtotal.data.repository.BooksRepositoryImpl
import com.example.testappsubtotal.model.Books
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val repository: BooksRepositoryImpl) : ViewModel() {

    private val _booksList = MutableLiveData<Books>()
    val bookList: LiveData<Books> get() = _booksList
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    init {
        Log.d("develop", "init")
        getBooksList()
    }

    private fun getBooksList() {
        viewModelScope.launch {
            _isLoading.value = true
            val response = repository.getBooksList()
            _booksList.value = response.body()
        }
        _isLoading.value = false
    }
}