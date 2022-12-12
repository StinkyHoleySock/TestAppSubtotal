package com.example.testappsubtotal.data.repository

import com.example.testappsubtotal.model.Books
import retrofit2.Response

interface BooksRepository {
    suspend fun getBooksList(): Response<Books>
}

