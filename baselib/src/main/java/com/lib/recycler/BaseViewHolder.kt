package com.lib.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(view: View, clickEnable: Boolean = true, longEnable: Boolean = false) : RecyclerView.ViewHolder(view), View.OnClickListener {

    lateinit var itemClick: (View, T?) -> Unit
    lateinit var itemLongClick: (View, T?) -> Unit
    var itemData: T? = null

    init {
        initView(view)
        if (clickEnable) {
            view.setOnClickListener {
                onItemClick(it, itemData)
            }
        }
        if (longEnable) {
            view.setOnLongClickListener {
                onItemLongClick(it, itemData)
                true
            }
        }
    }

    override fun onClick(v: View) {
        onItemClick(v, itemData)
    }

    open fun onItemClick(v: View, t: T?) {
        itemClick(v, t)
    }

    open fun onItemLongClick(v: View, t: T?) {
        itemLongClick(v, t)
    }

    fun onAttachedToWindow() {}

    fun onDetachedFromWindow() {}

    abstract fun bindData(position: Int)
    abstract fun initView(itemView: View)
}