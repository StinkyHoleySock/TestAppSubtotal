package com.example.testappsubtotal.data.api

import com.example.testappsubtotal.model.Books
import com.example.testappsubtotal.model.BooksInfoResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BookApiService {

    @GET("books/v1/volumes?q={query}+{filter}:keyes")
    suspend fun getBooksInfo(
        @Path("query") query: String,
        @Path("filter") filter: String,
        @Query("startIndex") startIndex: Int = 0,
        @Query("maxResults") maxResults: Int = 10,
        @Query("key") key: String,
    ): Response<BooksInfoResult>


    @GET("books/v1/volumes/{id}")
    suspend fun getOneBookInfo(
        @Path("id") query: String,
        @Query("key") key: String,
    ): Response<Books>


}