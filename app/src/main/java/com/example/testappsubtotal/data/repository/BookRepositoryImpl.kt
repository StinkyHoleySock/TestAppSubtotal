package com.example.testappsubtotal.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.testappsubtotal.data.api.BookApiService
import com.example.testappsubtotal.model.Books
import retrofit2.Response
import javax.inject.Inject

class BooksRepositoryImpl @Inject constructor(
    private val service: BookApiService
) : BooksRepository {

    init {
        Log.d("develop", "repo: ${service.toString()}")
    }

    override suspend fun getBooksList(): Response<Books> {
        return service.getBooksInfo(
            q = "flower",
            key = BookDataSource.API_KEY
        )
    }
}