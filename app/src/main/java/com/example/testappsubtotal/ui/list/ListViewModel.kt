package com.example.testappsubtotal.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.testappsubtotal.data.repository.BooksRepositoryImpl
import com.example.testappsubtotal.model.Books
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val repository: BooksRepositoryImpl) : ViewModel() {

    private val _booksList = MutableLiveData<PagingData<Books>>()
    val bookList: LiveData<PagingData<Books>> get() = _booksList

    suspend fun getBooksList(): LiveData<PagingData<Books>> {
        val response = repository.getBooksList().cachedIn(viewModelScope)
        _booksList.value = response.value
        return response
    }

}