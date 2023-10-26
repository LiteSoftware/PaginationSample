package com.udfsoft.pagination.data

import com.udfsoft.pagination.core.entity.MyItem

class MyRepository {

    fun loadData(nextPageNumber: Int, loadSize: Int): List<MyItem> {
        return DATA[nextPageNumber - 1]
    }

    companion object {

        private val DATA = generateData().chunked(20)

        private fun generateData() = mutableListOf<MyItem>().apply {
            (0..123).forEach {
                add(MyItem(it, it.toString()))
            }
        }

    }
}
