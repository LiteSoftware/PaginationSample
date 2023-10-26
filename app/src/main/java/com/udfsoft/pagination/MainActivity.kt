package com.udfsoft.pagination

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.recyclerview.widget.RecyclerView
import com.udfsoft.pagination.core.entity.MyItem
import com.udfsoft.pagination.data.MyDataSource
import com.udfsoft.pagination.data.MyDataSourceFactory
import com.udfsoft.pagination.data.MyRepository
import com.udfsoft.pagination.ui.MyPagingDataAdapter
import com.udfsoft.pagination.ui.theme.PaginationSampleTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContent {
//            PaginationSampleTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    Greeting("Android")
//                }
//            }
//        }
        val pagingConfig = PagingConfig(
            pageSize = 20,
            enablePlaceholders = false
        )

        val repository = MyRepository() // Замените на ваш репозиторий
        val dataSourceFactory = MyDataSource(repository)//MyDataSourceFactory(repository)
        val pager = Pager(pagingConfig) { dataSourceFactory }.flow
        val pagingData: Flow<PagingData<MyItem>> = pager
        val adapter = MyPagingDataAdapter()
        findViewById<RecyclerView>(R.id.recyclerView).adapter = adapter
        lifecycleScope.launch {
            pagingData.collect {
                adapter.submitData(it)
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PaginationSampleTheme {
        Greeting("Android")
    }
}