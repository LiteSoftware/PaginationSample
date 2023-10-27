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

package com.udfsoft.pagination

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.recyclerview.widget.RecyclerView
import com.udfsoft.pagination.core.entity.MyItem
import com.udfsoft.pagination.data.MyDataSource
import com.udfsoft.pagination.data.MyRepository
import com.udfsoft.pagination.ui.MyPagingDataAdapter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pagingConfig = PagingConfig(
            pageSize = 20,
            enablePlaceholders = false
        )

        val repository = MyRepository()
        val dataSourceFactory = MyDataSource(repository)
        val pager = Pager(pagingConfig) { dataSourceFactory }
        val pagingData: Flow<PagingData<MyItem>> = pager.flow
        val adapter = MyPagingDataAdapter()
        findViewById<RecyclerView>(R.id.recyclerView).adapter = adapter

        lifecycleScope.launch {
            pagingData.collect {
                adapter.submitData(it)
            }
        }
    }
}