package com.example.testappsubtotal.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.testappsubtotal.data.api.BookApiService
import com.example.testappsubtotal.model.Books

class BookDataSource(
    private val service: BookApiService,
)  {


    companion object {
        const val API_KEY = "AIzaSyCAbGrM2ff2bP6Y1T8kpcHLcaMKTTpMYR4"
        private const val INITIAL_LOAD_SIZE = 40
        private const val NETWORK_PAGE_SIZE = 10
    }
}