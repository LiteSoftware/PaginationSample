package com.udfsoft.pagination.data

import androidx.paging.DataSource
import com.udfsoft.pagination.core.entity.MyItem

class MyDataSourceFactory(
    private val repository: MyRepository
) : DataSource.Factory<Int, MyItem>() {

    //    override fun create(): DataSource<Int, MyItem> {
//        return MyDataSource(repository)
//    }
    override fun create(): DataSource<Int, MyItem> {
//        return MyDataSource(repository)
        throw UnsupportedOperationException()
    }
}