package com.lib.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(view: View, clickEnable: Boolean, longEnable: Boolean) : RecyclerView.ViewHolder(view), View.OnClickListener {

    lateinit var itemClick: (View, T?) -> Unit
    lateinit var itemLongClick: (View, T?) -> Unit
    var itemData: T? = null

    constructor(itemView: View) : this(itemView, true, false)
    constructor(itemView: View, clickEnable: Boolean) : this(itemView, clickEnable, false)

    init {
        initView(view)
        if (clickEnable) {
            view.setOnClickListener {
                itemClick(it, itemData)
            }
        }
        if (longEnable) {
            view.setOnLongClickListener {
                itemLongClick(it, itemData)
                true
            }
        }
    }

    override fun onClick(v: View) {
        onItemClick(v, itemData)
    }

    fun onItemClick(v: View, t: T?) {
        itemClick(v, t)
    }

    fun onItemLongClick(v: View, t: T) {
        itemLongClick(v, t)
    }

    fun onAttachedToWindow() {}

    fun onDetachedFromWindow() {}

    abstract fun bindData(position: Int)
    abstract fun initView(itemView: View)
}