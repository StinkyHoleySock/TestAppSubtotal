package com.example.testappsubtotal.data.repository

import com.example.testappsubtotal.data.api.BookApiService
import com.example.testappsubtotal.model.Books
import retrofit2.Response
import javax.inject.Inject

class BooksRepositoryImpl @Inject constructor(
    private val service: BookApiService
) : BooksRepository {

    override suspend fun getBooksList(query: String): Response<Books> {
        return service.getBooksInfo(
            q = query,
            key = BookDataSource.API_KEY
        )
    }
}