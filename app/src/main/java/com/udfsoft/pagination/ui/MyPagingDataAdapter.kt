package com.udfsoft.pagination.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.udfsoft.pagination.R
import com.udfsoft.pagination.core.entity.MyItem

class MyPagingDataAdapter : PagingDataAdapter<MyItem, MyViewHolder>(MyItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class MyItemDiffCallback : DiffUtil.ItemCallback<MyItem>() {

        override fun areItemsTheSame(oldItem: MyItem, newItem: MyItem) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: MyItem, newItem: MyItem) = oldItem.equals(newItem)

    }
}