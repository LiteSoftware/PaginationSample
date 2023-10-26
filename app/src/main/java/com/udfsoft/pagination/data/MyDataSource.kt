package com.udfsoft.pagination.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.udfsoft.pagination.core.entity.MyItem

class MyDataSource(private val repository: MyRepository) : PagingSource<Int, MyItem>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MyItem> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = repository.loadData(nextPageNumber, params.loadSize)
            LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = nextPageNumber + 1
            )
        } catch (e: Exception) {
            println("exception: $e")
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MyItem>): Int? {
        TODO("Not yet implemented")
    }
}