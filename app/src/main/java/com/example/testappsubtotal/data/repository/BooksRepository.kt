package com.example.testappsubtotal.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.testappsubtotal.model.Books

interface BooksRepository {
    suspend fun getBooksList(): LiveData<PagingData<Books>>
}

