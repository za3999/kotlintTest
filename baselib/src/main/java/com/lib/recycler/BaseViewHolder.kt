package com.lib.recycler

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(var itemView: View, var clickEnable: Boolean, var longEnable: Boolean) : RecyclerView.ViewHolder(itemView) {

    lateinit var itemClick: (View, T) -> Unit?
    lateinit var itemLongClick: (View, T) -> Unit?
    private var itemData: T? = null

    constructor(itemView: View) : this(itemView, true, false)
    constructor(itemView: View, clickEnable: Boolean) : this(itemView, clickEnable, false)

    init {
        if (clickEnable) {
        }
        if (longEnable) {

        }
    }

    fun onAttachedToWindow() {}

    fun onDetachedFromWindow() {}

    fun onBindData(position: Int, data: T) {
        itemData = data
        bindData(position, data)
    }

    abstract fun bindData(position: Int, t: T)
    abstract fun initView(itemView: View)

}