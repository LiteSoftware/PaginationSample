package com.udfsoft.pagination.ui

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.udfsoft.pagination.R
import com.udfsoft.pagination.core.entity.MyItem

class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(item: MyItem?) {
        item?.let {
            itemView.findViewById<TextView>(R.id.textView).text = it.text
        }
    }
}
