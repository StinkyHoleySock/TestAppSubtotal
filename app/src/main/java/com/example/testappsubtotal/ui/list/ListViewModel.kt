package com.example.testappsubtotal.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    fun getBooksList(query: String) {
        _isLoading.value = true
        viewModelScope.launch {
            if (query.isNotEmpty()) {
                val response = repository.getBooksList(query)
                _booksList.value = response.body()
            } else {
                _booksList.value = Books()
            }
            _isLoading.value = false
        }
    }
}