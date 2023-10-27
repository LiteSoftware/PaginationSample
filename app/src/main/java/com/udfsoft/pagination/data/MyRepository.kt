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

import com.udfsoft.pagination.core.entity.MyItem

class MyRepository {

    fun loadData(nextPageNumber: Int, loadSize: Int): List<MyItem> {
        return DATA[nextPageNumber - 1]
    }

    companion object {

        private val DATA = generateData().chunked(20)

        private fun generateData() = mutableListOf<MyItem>().apply {
            (0..10_000).forEach {
                add(MyItem(it, it.toString()))
            }
        }

    }
}
