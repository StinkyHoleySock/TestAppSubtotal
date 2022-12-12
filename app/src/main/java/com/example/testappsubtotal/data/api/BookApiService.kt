package com.example.testappsubtotal.data.api

import com.example.testappsubtotal.model.Books
import com.example.testappsubtotal.model.BooksInfoResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BookApiService {

    @GET(" ")
    suspend fun getBooksInfo(
        @Query("q") q: String,
        @Query("key") key: String,
//        @Path("filter") filter: String,
    ): Response<Books>


    @GET("{id}")
    suspend fun getOneBookInfo(
        @Path("id") query: String,
        @Query("key") key: String,
    ): Response<Books>


}