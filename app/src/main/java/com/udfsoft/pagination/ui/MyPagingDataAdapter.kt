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