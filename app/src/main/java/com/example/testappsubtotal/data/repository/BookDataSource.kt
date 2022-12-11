package com.example.testappsubtotal.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.testappsubtotal.data.api.BookApiService
import com.example.testappsubtotal.model.Books

class BookDataSource(
    private val service: BookApiService,
) : PagingSource<Int, Books>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Books> {
        val position = params.key ?: INITIAL_LOAD_SIZE
        val offset =
            if (params.key != null) ((position - 1) * NETWORK_PAGE_SIZE) + 1 else INITIAL_LOAD_SIZE
        return try {
            val response =
                service.getBooksInfo(
                    query = "flower",
                    filter = "intitle",
                    startIndex = offset,
                    maxResults = params.loadSize,
                    key = API_KEY
                ).body()?.data
            val nextKey = if (response?.isEmpty() == true) {
                null
            } else {
                position + (params.loadSize / NETWORK_PAGE_SIZE)
            }
            if (response != null) {
                LoadResult.Page(
                    data = response,
                    prevKey = null,
                    nextKey = nextKey
                )
            } else {
                LoadResult.Error(Exception())
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Books>): Int? {
        return null
    }


    companion object {
        private const val API_KEY = "AIzaSyCAbGrM2ff2bP6Y1T8kpcHLcaMKTTpMYR4"
        private const val INITIAL_LOAD_SIZE = 40
        private const val NETWORK_PAGE_SIZE = 10
    }
}