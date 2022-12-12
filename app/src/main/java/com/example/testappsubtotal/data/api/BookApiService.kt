package com.example.testappsubtotal.data.api

import com.example.testappsubtotal.model.Books
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BookApiService {
    @GET(" ")
    suspend fun getBooksInfo(
        @Query("q") q: String,
        @Query("key") key: String,
    ): Response<Books>
}