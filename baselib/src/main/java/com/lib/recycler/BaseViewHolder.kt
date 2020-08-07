package com.lib.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView

class BaseViewHolder<T>(itemView: View, var clickable: Boolean = false, var longClickable: Boolean = false) : RecyclerView.ViewHolder(itemView) {

    private var itemClick: ClickListener<View, T>? = null
    private var itemLongClick: ClickListener<View, T>? = null
    private var itemData: T? = null



    interface ClickListener<View, in T> {
        fun onItemClick(view: View, t: T)
    }

}