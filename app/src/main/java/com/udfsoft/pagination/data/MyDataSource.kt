/*
 *    Copyright 2023 javavirys
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

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